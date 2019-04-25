(function ($) {
    let uploadLikeNums = {};
    let uploadDisLikeNums = {};
    let localLikeNums = JSON.parse(localStorage.getItem("discuss_likes"));
    let localDisLikeNums = JSON.parse(localStorage.getItem("discuss_disLikes"));

    initBtns(localLikeNums, "discuss_likes", "comment");

    function initBtns() {
        activeLikeBtns(localLikeNums, "discuss_likes", "comment");
        activeLikeBtns(localDisLikeNums, "discuss_disLikes", "comment");
    }

    function activeLikeBtns(likeNums, storageName, type) {
        let className = "btn-" + type + "-";
        if (likeNums !== null) {
            let commentIds = likeNums.commentIds;
            let times = likeNums.times;
            //删除点赞缓存
            if (commentIds.length > 500) {
                commentIds.splice(0, 100);
                times.splice(0, 100);
                localStorage.setItem(storageName, JSON.stringify(likeNums));
            }
            let className = "btn-comment-";
            if (storageName === "discuss_likes")
                className += "up";
            else
                className += "down";
            $("." + className).each(function () {
                let commentId = $(this).parents("li").attr("id").split("_")[1];
                if (commentIds.indexOf(commentId) !== -1 && times[commentIds.indexOf(commentId)] !== 0) {
                    $(this).toggleClass(className + "_active");
                }
            });
        }
    }

    //    关闭前发送点赞数据
    window.onbeforeunload = function () {
        if (Object.keys(uploadLikeNums).length)
            $.ajax({
                type: "post"
                , url: "../updateLike"
                //异步必须为false  否则关闭窗口时无法发送
                , async: false
                , data: uploadLikeNums
            });
    };

    bindClick("discuss_likes", "comment");
    bindClick("discuss_disLikes", "comment");

    function bindClick(storageName, type) {
        let className = "btn-" + type + "-";
        if (storageName === "discuss_likes")
            className += "up";
        else
            className += "down";
        $("." + className).on("click", function () {
            //改造 localLikeNums 只有commentId
            let isChanged = $(this).hasClass(className + "_active");
            let likeNums = JSON.parse(localStorage.getItem(storageName));
            let commentId = $(this).parents("li").attr("id").split("_")[1];
            if (likeNums === null) {
                likeNums = {
                    commentIds: [commentId]
                    , times: [0]
                };
            }
            let commentIds = likeNums.commentIds;
            let times = likeNums.times;
            if (commentIds.indexOf(commentId) === -1) {
                commentIds.push(commentId);
                times.push(0);
            }
            let likes = $(this).find("span");
            let num = parseInt($(this).find("span").text());
            let index = commentIds.indexOf(commentId);
            let flag = times[index];
            if (isChanged) {
                times[index] = flag < -1 ? -1 : flag - 1;
                likes.text(num - 1);
            } else {
                times[index] = flag > 1 ? 1 : flag + 1;
                likes.text(num + 1);
            }
            uploadLikeNums[commentId] = times[commentIds.indexOf(commentId)];
            localStorage.setItem(storageName, JSON.stringify(likeNums));
            $(this).toggleClass(className + "_active");
        });
    }

    function sendAjax() {
        if (Object.keys(uploadLikeNums).length)
            $.ajax({
                type: "get"
                , url: "/comment/updateLike"
                //异步必须为false  否则关闭窗口时无法发送
                , async: false
                , data: uploadLikeNums
            });
    }

})(jQuery);

