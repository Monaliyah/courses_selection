
$(function(){
    getRoot();
});

function getRoot() {
    $.ajax({
        url:"/Root/getRoot",
        type:'POST',
        success:function(data){
            if(data!=null){
                render(data);
            }else{
                layer.msg("客户端请求出错")
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    });
}

function render(root) {
    $("#Root_Name").text(root.name);
    $("#Root_Rid").text(root.rid);
}

function toStatic() {
    $("#img1").hide();
    $("#Static").show();
    $("#SetOne").hide();
    $("#SetBatch").hide();
    $("#Settings").hide();
}

function toSetOne() {
    $("#img1").hide();
    $("#Static").hide();
    $("#SetOne").show();
    $("#SetBatch").hide();
    $("#Settings").hide();
}



function toSettings() {
    $("#img1").hide();
    $("#Static").hide();
    $("#SetOne").hide();
    $("#SetBatch").hide();
    $("#Settings").show();
}

function doSearchOne() {
    var id = $("#id").val();
    var type=0;
    if (id.length ===5) {
        type = 2;
    }else if(id.length ===10) {
        type=1;
    }
    if(type===0){
        doShowOne(0,type);
    }else {
        $.ajax({
            url: "/Login/doSearch/"+id,
            type: "POST",
            data: {
                id: id
            },
            success: function (data) {
                layer.closeAll();
                if (data.code == 200) {
                    doShowOne(data.obj,type);
                } else {
                    layer.msg(data.message);
                }
            },
            error: function () {
                layer.closeAll();
            }
        });
    }
}

var SPasswordSalt="3J0A1N21";
var RPasswordSalt="3J0A1N21";
var TPasswordSalt="3J0A1N21";

function doShowOne(dt,type) {
    if(dt===null){
        var flag=0;
    }
    var data={
        flag:1,
        type:"",
        id:"",
        name:""
    };
    if(dt===null) {
        data.flag=0;
        data.name="查无此人";
        data.type=type===1?"学生":"教师";
        data.id= $("#id").val();
    }else if(type===1){
        data.type="学生";
        data.id=dt.sid;
        data.name=dt.name;
    }else if(type===2){
        data.type="教师";
        data.id=dt.tid;
        data.name=dt.name;
    }
    var div=document.getElementById("result");
    var table=div.getElementsByTagName("table");
    if(table[0]!=null){
        div.removeChild(div.children[0]);
    }
    table=document.createElement("table");
    table.className="Table-Normal-1";


    if(type===0){
        tr=document.createElement("tr");
        td=document.createElement("td");
        td.innerText="格式错误无法查询";
        tr.appendChild(td);
        table.appendChild(tr);
        div.appendChild(table);
    }else {
        var thead = document.createElement("thead");
        var tr = document.createElement("tr");
        var td = document.createElement("td");
        td.innerText = "姓名";
        tr.appendChild(td);
        td = document.createElement("td");
        td.innerText = "id";
        tr.appendChild(td);
        td = document.createElement("td");
        td.innerText = "身份";
        tr.appendChild(td);
        td = document.createElement("td");
        td.innerText = "操作";
        tr.appendChild(td);
        thead.appendChild(tr);
        table.appendChild(thead);

        var tbody = document.createElement("tbody");

        tr = document.createElement("tr");
        td = document.createElement("td");
        if(data.flag===0){
            var input=document.createElement("input");
            td.appendChild(input);
            input.placeholder=data.name;
            input.required=true;
            input.id="newName";
            input.name="newName";
            input.className="Input-Normal-2";
            input.type="text";
        }else{
            td.innerText = data.name;
        }
        tr.appendChild(td);
        td = document.createElement("td");
        tr.appendChild(td);
        td.innerText = data.id;
        td = document.createElement("td");
        tr.appendChild(td);
        td.innerText =data.type;
        td = document.createElement("td");
        tr.appendChild(td);

        var button = document.createElement("button");
        button.className = "Button-Normal-2";
        button.type = "button";
        button.style.height = '30px';
        if (data.flag===0) {
            button.onclick = function () {
                data.name=$("#newName").val();
                doAddOne(data);
            };
            button.innerText = "添加";
        } else {
            button.onclick = function () {
                doDeleteOne(data);
            };
            button.innerText = "删除";
        }
        td.appendChild(button);
        tr.appendChild(td);
        tbody.appendChild(tr);
        table.appendChild(tbody);
        div.appendChild(table);
    }
}

function doAddOne(data){
    console.info(data);
    var Password = "123456";
    var salt = TPasswordSalt;
    var str = "" + salt.charAt(1) + salt.charAt(0) + Password + salt.charAt(0) + salt.charAt(6) + salt.charAt(2);
    var password = md5(str);
    if(data.type==="教师"){
        $.ajax({
            url: "/TUser/doAddOneTUser",
            type: "POST",
            data: {
                id:data.id,
                name:data.name,
                password:password
            },
            success: function (data) {
                layer.closeAll();
                if (data.code == 200) {
                    layer.msg(data.message);
                } else {
                    layer.msg(data.message);
                }
            },
            error: function () {
                layer.closeAll();
            }
        });
    }else{
        $.ajax({
            url: "/SUser/doAddOneSUser",
            type: "POST",
            data: {
                id:data.id,
                name:data.name,
                password:password
            },
            success: function (data) {
                layer.closeAll();
                if (data.code == 200) {
                    layer.msg(data.message);
                } else {
                    layer.msg(data.message);
                }
            },
            error: function () {
                layer.closeAll();
            }
        });
    }

}

function  doDeleteOne(data){
    if(data.type==="教师"){
        $.ajax({
            url: "/TUser/doDeleteOneTUser/"+data.id,
            type: "POST",
            success: function (data) {
                layer.closeAll();
                if (data.code == 200) {
                    layer.msg(data.message);
                } else {
                    layer.msg(data.message);
                }
            },
            error: function () {
                layer.closeAll();
            }
        });
    }else{
        $.ajax({
            url: "/SUser/doDeleteOneSUser/"+data.id,
            type: "POST",
            success: function (data) {
                layer.closeAll();
                if (data.code == 200) {
                    layer.msg(data.message);
                } else {
                    layer.msg(data.message);
                }
            },
            error: function () {
                layer.closeAll();
            }
        });
    }
}