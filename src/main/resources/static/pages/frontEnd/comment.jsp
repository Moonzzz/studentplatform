<%--
  Created by IntelliJ IDEA.
  User: Moon
  Date: 2019/1/8/008
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    String memberId = (String) session.getAttribute("memberId");
    memberId = "1";
%>
<link rel="stylesheet" href="<%=basePath%>/lib/layui/css/layui.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/iconfont.css"/>
<script type="text/javascript" src="<%=basePath%>/lib/layui/layui.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>

<style>
    .comment_user {
        margin: 2px 5px;
        display: inline-block;
    }

    .user_pic {
        margin-top: -30px;
        border-radius: 5px;
        width: 39px;
        height: 39px;
    }

    .comment_content {
        width: 90%;
        margin: 2px 5px;
        display: inline-block;
    }

    .user_txt {
        font-weight: bold;
    }

    .user_txt :hover {
        color: #d2aa60;
    }

    .comment_txt {
        margin: 2px 5px;
        font-size: 16px;
        color: #333;
        letter-spacing: 0.5px;
        overflow: hidden;
        word-break: break-all;
        text-overflow: ellipsis;
    }

    .comment_bottom {
        margin: 20px 5px;
    }

    .comment_time {
        color: #999;
        float: left;
    }

    .comment_like {
        height: 20px;
        float: right;
    }
</style>
<button onclick="localStorage.clear()" value="clear">clear</button>
<button onclick="sendAjax()" value="send">send</button>
<fieldset class="layui-elem-field">
    <legend>短评（）</legend>
    <ul>
        <c:forEach items="${comments}" var="comment">
            <li>
                <div class="layui-field-box">
                    <div class="comment_user">
                        <a href="reply.html"><img alt="user" class="user_pic" src="<%=basePath%>/images/${comment.memberImage}"></a>
                    </div>
                    <div class="comment_content">
                        <p class="user_txt"><a href="reply.html">${comment.memberName}</a></p>
                        <p class="comment_txt">${comment.content}</p>
                        <div class="comment_bottom">
                            <p class="comment_time">${comment.datePublished}</p>
                            <p class="comment_like"><a class="click_like" id="comment_${comment.commentId}"><i
                                    class="layui-icon layui-icon-dianzan"></i><b class="comment_likeNum">${comment.likeNum}</b></a></p>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</fieldset>
<form id="addComment" action="<%=basePath%>comment/addComment.action">
    <div id="editor" style="margin: 40px">
        <p>请在这里编写短评</p>
    </div>
    <input type="hidden" name="filmId" value="${filmId}">
    <input type="hidden" name="memberId" value="<%=memberId%>">
    <input type="hidden" name="content" id="content">
    <div class="row" style="margin: 40px">
        <div class="col-xs-4 col-sm-2  ">
            <button style="margin-left: 670px" type="submit" class="layui-btn"
                    id="send">提交影评
            </button>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        let E = window.wangEditor;
        let editor = new E('#editor');
        editor.create();
        //editor.txt.html('请输入影评内容...');
        //hidden赋值，传至后台发送影评
        $("#send").click(function () {
            let memberId = "<%=memberId%>";
            if (memberId === "null") {
                alert("请登录！");
                return false;
            }
            let text = editor.txt.text();
            $("#content").val(text);
        });
    });
    let uploadLikeNums = {};
    //    关闭前发送点赞数据
    $(window).bind('beforeunload', function () {
        if (Object.keys(uploadLikeNums).length)
            $.ajax({
                type: "get"
                , url: "<%=basePath%>comment/updateLike.action"
                //异步必须为false  否则关闭窗口时无法发送
                , async: false
                , data: uploadLikeNums
            });
    });

    function sendAjax() {
        if (Object.keys(uploadLikeNums).length)
            $.ajax({
                type: "get"
                , url: "<%=basePath%>comment/updateLike.action"
                //异步必须为false  否则关闭窗口时无法发送
                , async: false
                , data: uploadLikeNums
            });
    }

    $(function () {
        //删除点赞缓存
        let localLikeNums = JSON.parse(localStorage.getItem("movie_likes"));
        if (localLikeNums === null)
            return;
        let memberIds = localLikeNums.memberIds;
        let times = localLikeNums.times;
        if (memberIds.length > 500) {
            memberIds.splice(0, 100);
            times.splice(0, 100);
        }
        localStorage.setItem("movie_likes", JSON.stringify(localLikeNums));
        $(".click_like").each(function () {
            let commentId = this.id.split("_")[1];
            if (memberIds.indexOf(commentId) !== -1 && times[memberIds.indexOf(commentId)] !== 0) {
                $(this).find("i:first").addClass("layui-icon-dianzan_kuai");
            }
        });
    });
    $(".click_like").on("click", function () {
        //改造 localLikeNums 只有commentId
        let isChanged = $(this).find("i:first").hasClass("layui-icon-dianzan_kuai");
        let localLikeNums = JSON.parse(localStorage.getItem("movie_likes"));
        let memberId = this.id.split("_")[1];
        if (localLikeNums === null) {
            localLikeNums = {
                memberIds: [memberId]
                , times: [0]
            };
        }
        let memberIds = localLikeNums.memberIds;
        let times = localLikeNums.times;
        if (memberIds.indexOf(memberId) === -1) {
            memberIds.push(memberId);
            times.push(0);
        }
        if (isChanged) {
            let index = memberIds.indexOf(memberId);
            times[index] = times[index] < -1 ? -1 : times[index] - 1;
            $(this).find(".comment_likeNum").text(parseInt($(this).find(".comment_likeNum").text())-1);
        } else {
            let index = memberIds.indexOf(memberId);
            times[index] = times[index] > 1 ? 1 : times[index] + 1;
            $(this).find(".comment_likeNum").text(parseInt($(this).find(".comment_likeNum").text())+1);
        }
        uploadLikeNums[memberId] = times[memberIds.indexOf(memberId)];
        localStorage.setItem("movie_likes", JSON.stringify(localLikeNums));
        $(this).find("i:first").toggleClass("layui-icon-dianzan_kuai");
    });
</script>
