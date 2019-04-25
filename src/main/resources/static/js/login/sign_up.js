
$(function () {
    $("input:first").focus();
    $('#birthday').datetimepicker({
        format: 'YYYY-MM-DD'
    });
    $('#photo').change(function (ev) {
        let files = ev.target.files;
        if (files && files.length > 0) {
            let file = files[0];
            let URL = window.URL || window.webkitURL;
            let imgURL = URL.createObjectURL(file);
            $('#previewImg').attr('src', imgURL);
        }
    });
    $.validator.setDefaults({
        submitHandler: function () {
        }
    });
    $.validator.addMethod("isMobile", function (value, element) {
        let length = value.length;
        let mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length === 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    $('#form1').validate({
        rules: {
            username: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8
            },
            confirm_password: {
                required: true,
                minlength: 8,
                equalTo: "#password"
            }
        }
    });
    $('#form2').validate({
        rules: {
            gender: "required",
            tel: {
                minlength: 11,
                isMobile: true
            }
        }
    });

});