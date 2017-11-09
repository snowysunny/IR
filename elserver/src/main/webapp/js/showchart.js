/**
 * Created by hadoop on 2017/05/08.
 */
function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
//	var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
//	return y+"-"+m+"-"+d;
    return m + "-" + d;
}

var dataset;
var month_dataset;
var multiple_dataset;
var obj;
var rows;
var weiborows;
var wenshurows;

function ShowLineChart() {
    var strurl = "http://localhost:8080/mongodb";
    alert(strurl);
    $.get(strurl, function (data) {
        obj = data.replace(/^\s+|\s+$/g, "");
        obj = $.parseJSON(obj);
        rows = obj.rows;
        weiborows = obj.weiborows;
        wenshurows = obj.wenshurows;
        //	alert(rows[1].num);
        //	alert(obj.total);
        //  alert(obj.WeiboNum);
        //  document.write(obj.CommonNum);
        //折线图
        dataset = [
            {id: 1, sales: rows[0].num, year: GetDateStr(-6)},
            {id: 2, sales: rows[1].num, year: GetDateStr(-5)},
            {id: 3, sales: rows[2].num, year: GetDateStr(-4)},
            {id: 4, sales: rows[3].num, year: GetDateStr(-3)},
            {id: 5, sales: rows[4].num, year: GetDateStr(-2)},
            {id: 6, sales: rows[5].num, year: GetDateStr(-1)},
            {id: 7, sales: rows[6].num, year: GetDateStr(0)}
        ];

        var myLineChart;

        //	function doOnLoad() {
        myLineChart =  new dhtmlXChart({
            view:"spline",
            container:"chartbox",
            value:"#sales#",
            item:{
                borderColor: "#ffffff",
                color: "#000000"
            },
            line:{
                color:"#ff9900",
                width:3
            },
            xAxis:{
                template:"'#year#"
            },
            offset:0,
            yAxis:{
                start:0,
                end:40000,
                step:4000,
                template:function(obj){
                    return (obj%4000?"":obj)
                }
            }


        });
        myLineChart.parse(dataset,"json");
        //	}

        //饼状图
        month_dataset = [
            { sales:obj.WeiboNum, month:"Weibo", color: "#ee3639" },
            { sales:obj.CommonNum, month:"Common", color: "#ee9e36" },
            { sales:obj.WenshuNum*100, month:"Wenshu", color: "#eeea36" }
    //        { sales:40, month:"Apr", color: "#a9ee36" },
    //        { sales:70, month:"May", color: "#36d3ee" },
    //        { sales:80, month:"Jun", color: "#367fee" },
    //        { sales:60, month:"Jul", color: "#9b36ee" }
        ];
        var myPieChart;
        myPieChart = new dhtmlXChart({
            view:"pie",
            container:"chart1",
            value:"#sales#",
            color:"#color#",
            label:"#month#",
            pieInnerText:"#sales#",
            shadow:0
        });
        myPieChart.parse(month_dataset,"json");

        //条行图
        multiple_dataset = [
            { sales:rows[0].num+1000, sales2:wenshurows[0].num+1000, sales3:weiborows[0].num+1000, year:GetDateStr(-6) },
            { sales:rows[1].num+1000, sales2:wenshurows[1].num+1000, sales3:weiborows[1].num+1000, year:GetDateStr(-5) },
            { sales:rows[2].num+1000, sales2:wenshurows[2].num+1000, sales3:weiborows[2].num+1000, year:GetDateStr(-4) },
            { sales:rows[3].num+1000, sales2:wenshurows[3].num+1000, sales3:weiborows[3].num+1000, year:GetDateStr(-3) },
            { sales:rows[4].num+1000, sales2:wenshurows[4].num+1000, sales3:weiborows[4].num+1000, year:GetDateStr(-2) },
            { sales:rows[5].num+1000, sales2:wenshurows[5].num+1000, sales3:weiborows[5].num+1000, year:GetDateStr(-1) },
            { sales:rows[6].num+1000, sales2:wenshurows[6].num+1000, sales3:weiborows[6].num+1000, year:GetDateStr(0) }
        ];

        var myBarChart;

        myBarChart =  new dhtmlXChart({
            view:"bar",
            container:"chart2",
            value:"#sales#",
            color: "#58dccd",
            gradient:"rising",
            tooltip:{
                template:"#sales#"
            },
            width:60,
            xAxis:{
                template:"'#year#"
            },
            yAxis:{
                start:0,
                step:4000,
                end:40000
            },
            legend:{
                values:[{text:"Common",color:"#58dccd"},{text:"Wenshu",color:"#a7ee70"},{text:"Weibo",color:"#36abee"}],
                valign:"middle",
                align:"right",
                width:90,
                layout:"y"
            }
        });
        myBarChart.addSeries({
            value:"#sales2#",
            color:"#a7ee70",
            tooltip:{
                template:"#sales2#"
            }
        });
        myBarChart.addSeries({
            value:"#sales3#",
            color:"#36abee",
            tooltip:{
                template:"#sales3#"
            }
        });
        myBarChart.parse(multiple_dataset,"json");


    });


}


