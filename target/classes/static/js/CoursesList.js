function button_onclick(but,inp) {
    var button=document.getElementById(but);
    var input=document.getElementById(inp);
    if(input.checked){
        input.checked=false;
        button.style.backgroundColor="#fcf8e3";
    }
    else{
        input.checked=true;
        button.style.backgroundColor="gray";
    }
}

function doReSet() {
    var s=new Array();
    s[0]=document.getElementById("YesButton");
    s[1]=document.getElementById("NotButton");
    s[2]=document.getElementById("Session1Button");
    s[3]=document.getElementById("Session2Button");
    s[4]=document.getElementById("Session3Button");
    s[5]=document.getElementById("Session4Button");
    s[6]=document.getElementById("01Button");
    s[7]=document.getElementById("02Button");
    s[8]=document.getElementById("03Button");
    s[9]=document.getElementById("04Button");
    s[10]=document.getElementById("05Button");
    s[11]=document.getElementById("06Button");
    s[12]=document.getElementById("07Button");
    s[13]=document.getElementById("08Button");
    s[14]=document.getElementById("09Button");
    s[15]=document.getElementById("10Button");
    s[16]=document.getElementById("11Button");
    s[17]=document.getElementById("12Button");
    s[18]=document.getElementById("13Button");
    for(var i=0;i<s.length;i++){
        s[i].style.backgroundColor="#fcf8e3";
    }
    s[0]=document.getElementById("YesInput");
    s[1]=document.getElementById("NotInput");
    s[2]=document.getElementById("Session1Input");
    s[3]=document.getElementById("Session2Input");
    s[4]=document.getElementById("Session3Input");
    s[5]=document.getElementById("Session4Input");
    s[6]=document.getElementById("01Input");
    s[7]=document.getElementById("02Input");
    s[8]=document.getElementById("03Input");
    s[9]=document.getElementById("04Input");
    s[10]=document.getElementById("05Input");
    s[11]=document.getElementById("06Input");
    s[12]=document.getElementById("07Input");
    s[13]=document.getElementById("08Input");
    s[14]=document.getElementById("09Input");
    s[15]=document.getElementById("10Input");
    s[16]=document.getElementById("11Input");
    s[17]=document.getElementById("12Input");
    s[18]=document.getElementById("13Input");
    for(i=0;i<s.length;i++){
        s[i].checked=false;
    }
}

function doSearch() {

    getOptCoursesList();

}

function toggleByHeight(){
    var containerStyle1 =  document.getElementById("Max").style;
    var containerStyle2 =  document.getElementById("Session").style;
    var containerStyle3 =  document.getElementById("SubType1").style;
    var containerStyle4 =  document.getElementById("SubType2").style;
    var containerStyle5 =  document.getElementById("SubType3").style;
    console.info(containerStyle1.display);
    var buttonExpand = document.getElementById("toggleButton");
    if (containerStyle1.display =="none") {
        containerStyle1.display = "block";
        containerStyle2.display = "block";
        containerStyle3.display = "block";
        containerStyle4.display = "block";
        containerStyle5.display = "block";
        buttonExpand.innerText = '收起';
    } else {
        containerStyle1.display = "none";
        containerStyle2.display = "none";
        containerStyle3.display = "none";
        containerStyle4.display = "none";
        containerStyle5.display = "none";

        buttonExpand.innerText = '展开 ';
    }
}

function getOptCoursesList() {
    $.ajax({
        url:"/Courses/getCoursesList",
        type:'POST',
        success:function(data){
            if(data!=null){
                doShow(data);
            }else{
                layer.msg("客户端请求出错")
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    });
}

function doShow(CoursesVos) {
    var s=new Array();
    s[0]=document.getElementById("NotInput").checked;
    s[1]=document.getElementById("YesInput").checked;
    s[2]=document.getElementById("Session1Input").checked;
    s[3]=document.getElementById("Session2Input").checked;
    s[4]=document.getElementById("Session3Input").checked;
    s[5]=document.getElementById("Session4Input").checked;
    s[6]=document.getElementById("01Input").checked;
    s[7]=document.getElementById("02Input").checked;
    s[8]=document.getElementById("03Input").checked;
    s[9]=document.getElementById("04Input").checked;
    s[10]=document.getElementById("05Input").checked;
    s[11]=document.getElementById("06Input").checked;
    s[12]=document.getElementById("07Input").checked;
    s[13]=document.getElementById("08Input").checked;
    s[14]=document.getElementById("09Input").checked;
    s[15]=document.getElementById("10Input").checked;
    s[16]=document.getElementById("11Input").checked;
    s[17]=document.getElementById("12Input").checked;
    s[18]=document.getElementById("13Input").checked;

    var i=0,j=0;
    var showList1=new Array();
    if(s[0]&&!s[1]){
        for(i=0;i<CoursesVos.length;i++){
            if(CoursesVos[i].nowStudents<CoursesVos[i].maxStudents)
                showList1.push(CoursesVos[i]);
        }
    } else if(!s[0]&&s[1]){
        for(i=0;i<CoursesVos.length;i++){
            if(CoursesVos[i].nowStudents>=CoursesVos[i].maxStudents)
                showList1.push(CoursesVos[i]);
        }
    }else {
        for(i=0;i<CoursesVos.length;i++){
            showList1.push(CoursesVos[i]);
        }
    }

    var showList2=new Array();
    var year=new Date().getFullYear();
    for(j=0;j<4;j++){
        if(s[2+j]){
            for(i=0;i<showList1.length;i++){
                if(showList1[i].session==(year-3+j))
                    showList2.push(showList1[i]);
            }
        }
    }
    if(showList2[0]==null){
        for(i=0;i<showList1.length;i++){
            showList2.push(showList1[i]);
        }
    }

    var showList3=new Array();
    for(j=0;j<13;j++){
        if(s[6+j]){
            for(i=0;i<showList2.length;i++){
                if(showList1[i+j].subtype==(1+j))
                    showList3.push(showList2[i]);
            }
        }
    }
    if(showList3[0]==null){
        for(i=0;i<showList2.length;i++){
            showList3.push(showList2[i]);
        }
    }

    var div=document.getElementById("hey");
    var table=div.getElementsByTagName("table");
    if(table[0]!=null){
        div.removeChild(div.children[0]);
    }
    table=document.createElement("table");
    table.className="Table-Normal-2";
    table.id="List";
    var thead=document.createElement("thead");
    var tr=document.createElement("tr");
    var td=document.createElement("td");

    td.innerText="课程名称";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="授课教师";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="课程类别";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="学分";
    tr.appendChild(td);
    td=document.createElement("td");
    td.innerText="学生数";
    tr.appendChild(td);
    thead.appendChild(tr);
    table.appendChild(thead);

    var tbody=document.createElement("tbody");
    for(i=0;i<showList3.length;i++) {
        var a=showList3[i];
        tr=document.createElement("tr");
        tr.id=a.cid;
        td=document.createElement("td");
        td.innerText=a.cname;
        tr.appendChild(td);
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.tname;
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
        td.innerText=a.credits;
        td=document.createElement("td");
        tr.appendChild(td);
        td.innerText=a.nowStudents+"/"+a.maxStudents;
        td=document.createElement("td");
        tr.appendChild(td);

        var button=document.createElement("button");
        if(showList3[i].flag){
            button.className="Button-Normal-2";
            button.type="button";
            button.style.height='30px';
            tr.style.backgroundColor="#90EE90";
            button.value=a.cid;
            button.onclick=function(){
                dropCourses(this.value);
            };
            button.innerText="退课";
        } else {
            button.className = "Button-Normal-2";
            button.type = "button";
            button.style.height='30px';
            button.value=a.cid;
            button.onclick=function(){
                getCourses(this.value);
            };
            button.innerText = "选课";
        }
        td.appendChild(button);
        tr.appendChild(td);
        tr.id=a.cid;
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    div.appendChild(table);
}

function getCourses(cid) {

    $.ajax({
        url:'/Courses/doChoose/'+cid,
        type:'POST',
        data:{
            cid:cid
        },
        success:function (data) {
            if(data.code==200){
                doSearch();
            }else{
                layer.msg(data.message)
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    })

}

function dropCourses(cid) {
    $.ajax({
        url:'/Courses/doDrop/'+cid,
        type:'POST',
        data:{
            cid:cid
        },
        success:function (data) {
            if(data.code==200){
                doSearch();
            }else{
                layer.msg(data.message)
            }
        },
        error:function () {
            layer.msg("客户端请求出错")
        }
    })
}