$(function(){
    background();
    getSUser();
});

function render(sUser) {
    $("#SUser_Name").text(sUser.name);
    $("#SUser_Sid").text(sUser.sid);
}

function toStatic() {
    $.ajax({
        url:"/SInfStatic/getSInfStatic",
        type:'POST',
        success:function(data){
            if(data!=null){
                if(data.sex==1){
                    $("#SUserSex").text("男");
                }else{
                    $("#SUserSex").text("女");
                }
                switch (data.documentType) {
                    case 1:
                        $("#SUserDocumentType").text("居民身份证");
                        break;
                    case 2:
                        $("#SUserDocumentType").text("其他");
                        break;
                    default:
                        $("#SUserDocumentType").text("居民身份证");
                        break;
                }
                $("#SUserDocumentId").text(data.documentId);
                switch (data.nationality) {
                    case 1:
                        $("#SUserNationality").text("汉族");
                        break;
                    default:
                        $("#SUserNationality").text("其他");

                        break;
                }
                $("#img1").hide();
                $("#Static").show();
                $("#SchoolRoll").hide();
                $("#Point").hide();
            }else{
                layer.msg("客户端请求出错")
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    });
}

function toSchoolRoll() {
    $.ajax({
        url:"/SInfSchoolRoll/getSInfSchoolRoll",
        type:'POST',
        success:function(data){
            if(data!=null){
                $("#SUserGrade").text(data.grade);
                $("#SUserCollegeName").text(data.collegeName);
                $("#SUserProfessionalName").text(data.professionalName);
                $("#SUserClassName").text(data.className);
                if(data.category==1) {
                    $("#SUserCategory").text("本科");
                }else{
                    $("#SUserCategory").text("专科");
                }
                for(var i=0;i<4;i++){
                    if(data.registration.charAt(i)=='1') {
                        $("#SUserRegister"+(i+1)).text("已报到");
                    }else {
                        $("#SUserRegister"+(i+1)).text("未报到");
                    }
                }
                $("#img1").hide();
                $("#Static").hide();
                $("#SchoolRoll").show();
                $("#Point").hide();
            }else{
                layer.msg("客户端请求出错")
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    });
}

function toCoursesSelection() {
    window.location.href="/Courses/toCoursesList"
}

function getSUser() {
    $.ajax({
        url:"/SUser/getSUser",
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

function toPointList() {

    $.ajax({
        url:"/Orders/getPointList",
        type:'POST',
        success:function(data){
            if(data!=null){
                doShowPointList(data);
                $("#img1").hide();
                $("#Static").hide();
                $("#SchoolRoll").hide();
                $("#Point").show();
            }else{
                layer.msg("客户端请求出错")
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    });


}

function doShowPointList(PointList) {

    var div=document.getElementById("SInfPoint");
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
    td.innerText="授课教师";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="总成绩分";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="平时分";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="测试分";
    tr.appendChild(td);
    thead.appendChild(tr);
    table.appendChild(thead);

    var tbody=document.createElement("tbody");
    for(i=0;i<PointList.length;i++) {
        var a=PointList[i];
        tr=document.createElement("tr");
        tr.id=a.oid;
        td=document.createElement("td");
        td.innerText=a.cname;
        tr.appendChild(td);
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.tname;
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.totalScore;
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.regularScore;
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.termScore;
        td=document.createElement("td");
        tr.appendChild(td);
        tr.id=a.oid;
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    div.appendChild(table);

}
