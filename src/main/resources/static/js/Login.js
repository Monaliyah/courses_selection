$(function(){
    background();
});


var SPasswordSalt="3J0A1N21";
var RPasswordSalt="3J0A1N21";
var TPasswordSalt="3J0A1N21";

function doLogin(){
    $("#LoginForm").validate({
        submitHandler: function (form){

            ShowLoading();
            var Password = $("#Password").val();
            var id= $("#id").val();
            var salt;
            if(id.length==10) {
                salt = SPasswordSalt;
            }else if(id.charAt(0)=='9'){
                salt = RPasswordSalt;
            }else {
                salt = TPasswordSalt;
            }
            var str = "" + salt.charAt(1) + salt.charAt(0) + Password + salt.charAt(0) + salt.charAt(6) + salt.charAt(2);
            var password = md5(str);
            $.ajax({
                url: "/Login/doLogin",
                type: "POST",
                data: {
                    id: id,
                    password: password
                },
                success:function (data) {
                    layer.closeAll();
                    if (data.code == 200) {
                        layer.msg("成功");
                        console.info(data.obj);
                        window.location.href="/"+data.obj;
                    } else {
                        layer.msg(data.message);
                    }
                },
                error: function () {
                    layer.closeAll();
                }
            });
        }
    });
}

function Input_Id_Pattern() {
    var input_text=$("#id").val();
    var reg = new RegExp("[0-9]");
    var reg1=new RegExp("[1-9]");
    if (input_text.length==0) {
        layer.msg("账号不能为空",{offset:[500,1161]});
    } else if (!reg.test(input_text.charAt(input_text.length - 1))) {
        layer.msg("只能输入数字",{offset:[500,1161]});
    } else {
        if (input_text.length != 5) {
            console.info("2");
            if (input_text.length == 10) {
                if (!reg1.test(input_text.charAt(input_text.length - 1))) {
                    layer.msg("账号格式错误", {offset: [500, 1161]});
                }
            }else {
                layer.msg("账号为5或10位",{offset:[500,1155]});
            }
        }
    }
}

function Input_Password_Pattern() {
    var input_text=$("#Password").val();
    if (input_text.length<6||input_text.length>16) {
        layer.msg("密码为6-16位",{offset:[500,1159]});
    }
}

function doForgetPassword() {

}