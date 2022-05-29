
$(function(){
    getTUser();
});

function getTUser() {
    $.ajax({
        url:"/TUser/getTUser",
        type:'POST',
        success:function(data){
            if(data!=null){
                console.info(data);
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
function render(tUser) {
    $("#TUser_Name").text(tUser.name);
    $("#TUser_Tid").text(tUser.tid);
    var time = new Date().getFullYear();
    $("#Session1").text(time-3);
    $("#Session1").value=time-3;
    $("#Session2").text(time-2);
    $("#Session2").value=time-2;
    $("#Session3").text(time-1);
    $("#Session3").value=time-1;
    $("#Session4").text(time);
    $("#Session4").value=time;
}

function toStatic() {
    $("#img1").hide();
    $("#Static").show();
    $("#AddCourses").hide();
    $("#Courses").hide();
}

function toAddCourses() {
    $("#img1").hide();
    $("#Static").hide();
    $("#AddCourses").show();
    $("#Courses").hide();

}

function doAddCourses() {
    $("#AddCoursesForm").validate({
        submitHandler: function (form) {
            ShowLoading();
            $.ajax({
                url: "/Courses/doAddCourses",
                type: "POST",
                data: {
                    cid:0,
                    cname: $("#cname").val(),
                    tid :null,
                    tname:null,
                    subtype: $("select[name='SubType'] option:selected").val(),
                    maxStudents: $("#MaxStudents").val(),
                    nowStudent:0,
                    credits: $("#Credits").val(),
                    duration:0,
                    session: $("select[name='Session'] option:selected").val(),
                    flag:false
                },
                success:function (data) {
                    layer.closeAll();
                    if (data.code == 200) {
                        layer.msg("成功");
                    } else {
                        layer.msg(data.message);
                    }
                },
                error: function () {
                    layer.closeAll();
                }
            });
        }
    })
}

function toCoursesList() {

    $.ajax({
        url:"/Courses/getCoursesListByOneTUser",
        type:'POST',
        success:function(data){
            if(data!=null){
                doShowCoursesList(data);
                $("#img1").hide();
                $("#Static").hide();
                $("#AddCourses").hide();
                $("#Courses").show();
            }else{
                layer.msg("客户端请求出错")
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    });


}

function doShowCoursesList(CoursesList) {

    var div=document.getElementById("Courses");
    var table=div.getElementsByTagName("table");
    if(table[0]!=null){
        div.removeChild(div.children[0]);
    }
    table=document.createElement("table");
    table.className="Table-Normal-1";
    table.style.width = "600px";
    table.id="PointList";
    var thead=document.createElement("thead");
    var tr=document.createElement("tr");
    var td=document.createElement("td");

    td.innerText="课程名称";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="课程类型";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="开设年级";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="学生数";
    tr.appendChild(td);
    thead.appendChild(tr);
    table.appendChild(thead);

    var tbody=document.createElement("tbody");
    for(var i=0;i<CoursesList.length;i++) {
        var a=CoursesList[i];
        tr=document.createElement("tr");
        tr.id=a.cid;
        td=document.createElement("td");
        td.innerText=a.cname;
        tr.appendChild(td);
        td=document.createElement("td");
        tr.appendChild(td);
        switch (a.subtype) {
            case 1:
                td.innerText="理学类";
                break;
            case 2:
                td.innerText="工学类";
                break;
            case 3:
                td.innerText="哲学类";
                break;
            case 4:
                td.innerText="历史学类";
                break;
            case 5:
                td.innerText="文学类";
                break;
            case 6:
                td.innerText="艺术学类";
                break;
            case 7:
                td.innerText="教育学类";
                break;
            case 8:
                td.innerText="法学类";
                break;
            case 9:
                td.innerText="经济学类";
                break;
            case 10:
                td.innerText="管理学类";
                break;
            case 11:
                td.innerText="农学类";
                break;
            case 12:
                td.innerText="医学类";
                break;
            case 13:
                td.innerText="军事学类";
                break;
        }
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.session;
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.nowStudents+"/"+a.maxStudents;
        td=document.createElement("td");
        tr.appendChild(td);
        tr.id=a.cid;
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    div.appendChild(table);

}

