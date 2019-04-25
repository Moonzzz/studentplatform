(function ($) {
    editormd.markdownToHTML("blogContent", {
        htmlDecode: "style,script,iframe", //可以过滤标签解码
        emoji: true,
        taskList: true,
        tex: true,               // 默认不解析
        flowChart: true,         // 默认不解析
        sequenceDiagram: true, // 默认不解析
        codeFold: true
    });
    let WE = window.wangEditor;
    let editor = new WE('#comment');
    editor.customConfig = {
        menus: ["emoticon", "image", "undo"],
        uploadImgShowBase64: true,
    };
    editor.create();
    //hidden赋值，传至后台发送影评
    $("#send").click(function () {
        let postId = parseInt($(this).attr("name"));
        let formData = {
            postId: postId,
            content: editor.txt.html()
        };
        sendAjax('../addComment', formData);
    });
    $('.btn-comment-reply').on("click", function () {
        let comment = $(this).parent().parent().parent();
        let reply = comment.children(":last-child");
        if (reply.attr("class") !== "comment-reply") {
            $('#sendReply').parent().parent().remove();
            reply = createElement("div", "comment-reply");
            let template = String(input_group);
            reply.innerHTML = template.substring(template.indexOf("<"), template.lastIndexOf("*/"));
            comment.append(reply);
        } else {
            reply.remove();
        }

        let editor = new WE('#reply-tool', '#reply-content');
        editor.customConfig = {
            menus: ["emoticon", "image", "undo"],
            uploadImgShowBase64: true,
        };
        editor.create();
        $('#sendReply').on("click", function () {
            let comment = $(this).parents(".comment-wrapper");
            console.log(comment.find(".author")[0]);
            let formData = {
                fatherId: comment.parents(".comment").attr("id").split("_")[1],
                replyToUsername: comment.find(".author")[0].innerText,
                postId: $("#content").attr("name"),
                content: editor.txt.html()
            };
            sendAjax('../addReply', formData);
        });
    });

    function input_group() {
        /*<div class="input-group" style="width:100%;text-align:left">
        <div id="reply-tool" class="toolbar"></div>
        <div id="reply-content" class="text"></div>
        <button class="btn btn-submit" id="sendReply"><i class="icon-reply"> 提交</i></button>
        </span>
        </div>*/
    }

    function sendAjax(url, formData) {
        $.ajax({
            type: 'post',
            url: url,
            data: formData,
            error: function (XMLHttpRequest, errorThrown) {
                alert('status:' + XMLHttpRequest.status + ', status text: ' + XMLHttpRequest.statusText + ',errorThrown: ' + errorThrown);
            },
            success: function (data) {
                if (data === "true")
                    window.location.reload();
                else
                    alert("发表失败！服务器或网络异常！");
            }
        });
    }

    function createElement(tagName, className) {
        let ele = document.createElement(tagName);
        ele.classList.add(className);
        return ele;
    }

})(jQuery);
