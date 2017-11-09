/**
 * Created by hadoop on 2017/04/05.
 */

function loadjson(){
    var strurl="http://localhost:8080/getJson?query=";
//    strurl += document.getElementById("strquery").value;

    strurl +=  $("#strquery").val();
    var strHtml;
    var datajson = $.getJSON(strurl,function(data){
        return data;
 //      return data;
        /* strHtml = "";
        var intHtml = 0;//存储数据的变量src="js/jquery-1.8.3.min.js"
        intHtml = data.rows.length;
        var index=0;
        strHtml = "{\"total\":" + intHtml +",\"rows\": [";
        $.each(data.rows,function(infoIndex,info){
            strHtml += "{\"id\":"+info["id"]+",";
            strHtml += "\"title\":\""+info["title"]+"\",";
            strHtml += "\"time\":\""+info["time"]+"\",";
            strHtml += "\"content\":\""+info["content"]+"\",";
            strHtml += "\"url\":\"" + info["url"] + "\"}";
            index++;
            if(index < intHtml)
                strHtml += ",";
        })
        strHtml += "]}";
        return strHtml;*/
    })


    var strHtml1 = {"total":8,"rows": [
        {"id":53, "title":"新英菲尼迪M长轴距版将于6月8日上市", "time":"2017-03-18","content":"【新浪讯】长版将于6月8日正式上市，共两款车型M25L与M35hL，其中M35hL为车型。新英菲尼迪M长轴距车型是专为中国市场打造的中级豪华轿车，其轴距加长了150mm，达到3050mm，使得后排空间更加阔绰。\n英菲尼迪M长轴距版的核心亮点之一便是轴距加长了150mm，达到同级领先的3050mmm，为后排乘客提供了更尊崇的后排空间和更舒适的乘坐体验。对驾驶乐趣的追求是英菲尼迪一贯的品牌诉求，轴距加长后的英菲尼迪M长轴距版将依然拥有精准的操控性，不会为了空间向操控妥协，降低车辆的驾驶乐趣。后排侧窗遮阳帘、后窗电动遮阳帘，后排电、后排控制前排座椅等贴心人性化配置均成为标配，使英菲尼迪M长轴距版在同车型中更具竞争力。\n整车犹如一头奔跑中的矫健，沉稳而迅捷。侧身设计则汲取了Essence以海浪等大自然力量为设计灵感的。低拉隆起的进气隔栅、般健硕优美的前翼子板、律动飘逸的双弧腰线，动感中蕴含优雅，细腻中彰显大气，突破传统中级豪华轿车的一贯风格。\n英菲尼迪M长轴距高性能混合动力版车型M35hL搭载的3.5升V6高性能混合动力系统，是唯一一款获得2012“沃德十大奖”的混合动力发动机。凭借先进的单电机、并联混合动力设计以及高速电机控制技术，该系统能够提供与传统VQ37发动机相同水平的动力性能，同时减少30%的，在性能和燃油经济性之间实现了最佳平衡。无论是拥挤的城市道路还是畅通的高速公路，该系统都不会让驾驶者感觉到任何速度与动力的损失，反而使其在感受驾驶乐趣的同时，增强了为环保出行贡献力量的自豪感与责任感。\n在自然吸气动力方面，英菲尼迪M长轴距版M25L搭载唯一一款连续14年当选沃德汽车世界(Ward’s Automotive World)评选的“年度十佳发动机”的VQ系列发动机，拥有173kW/6400rpm的最大输出和253Nm/4800rpm的最大。这款发动机的汽缸顶端由大块铝合金构成，重量轻，运行快速且安静。60度的夹角和120度的曲柄角度，保证了发动机运转时的平衡性，有效降低震动和噪声。双搭配每缸4的设计，再加上S连续可变气门正时技术的应用，加强了发动机的性能和响应速度。\n同时，令澎湃动力收放自如的7速手自一体，带来流畅而平顺的驾驶感受。该变速箱应用了智能化的自适应换挡控制功能(ASC)，可以在坡道和弯路上自动判断并启用挡位保持功能，以减少车身的顿挫感，使驾驶者即便在激烈驾控下，依然可以轻松享受速度。此外，英菲尼迪M长轴距版车型上独特的“节能踏板”通过油门踏板产生反作用力来协助驾驶者以更经济的形式进行驾驶，可使燃油经济性提升5%。\n","url":"url:http://cz.auto.sina.com.cn/"},
        {"id":28,"title":"标致307现金优惠8000元 有现车","time":"2017-03-18","content":"【新浪汽车讯】近日新浪常州站编辑从常州冠亚恒通汽车销售服务有限公司了解到，加装版车型目前现车销售，购车现金优惠8000元。感兴趣的朋友可进店详询，具体降价明细如下表：\n车型名称\n厂家指导价\n市场价\n优惠促销\n备注\n信息采集时间：2012-4-25制表：新浪汽车\n车市信息变化频繁，具体售价请与当地经销商商谈。\n307的前脸外观酷似狮子威俊的面孔，尤其两个前大灯好像狮子的双眼炯炯放光，这是沿袭了标致车系共有的特征。从侧面看，个性十足，整体流线柔润和谐，前翼子板滑过车身上部、弧形顶盖与后三角窗、后尾翼融为一体，很自然地勾勒出后备箱的弯曲轮廓，后备箱与后尾翼过渡自然，车后灯巧妙地镶嵌其中。\n整体外型平衡匀称，强劲与和谐自然流露。在动力性能方面，东风标致307与同系列其它车型的设计相同，横置，前置前，四轮独立，主动、被动安全装置齐全，人性化设计处处得到充分体现。\n在新浪汽车口碑榜上，有车友表示“空间大，外形漂亮(就喜欢小狮子的大嘴和大屁股)，地盘扎实，操控好，好，乘坐舒适。”另外一位车友表示“外形帅气，空间适合一家三口够用。三档后跑起来较快，超车流畅。”\n","url":"url:http://cz.auto.sina.com.cn/"},
        {"id":80,"title":"常州瑞达奥迪A7全系车型可享9折优惠","time":"2017-03-18","content":"【新浪汽车讯】常州钟楼瑞达近期A7全系车型可享9折优惠，订车更有万元好礼相赠。具体优惠可来店详谈。数量有限，预购从速！\n优雅与动感的完美融合\n奥迪A7 Sportback在造型设计上力求简约而优美的风格，柔和整洁、富有张力的表面与性格鲜明的轮廓特征。和奥迪品牌的其他车型一样，奥迪A7 Sportback的设计十分强调整体感，整车如同由一整块金属打磨而成。车身表面的所有线条和表面都清晰明了，没有什么东西是多余的。与此同时，汽车又不能只是冰冷的工业产品，人们需要一定的情感元素。奥迪A7 Sportback不仅延续了家族设计语言，还丰富了奥迪品牌的情感特征。\n奥迪A7\n流畅优雅的车身线条\n其车身造型整体感很强，各元素之间相互呼应；表面处理简洁、硬朗；线条运用精准、流畅，每一处都展现出融合科技与时尚的美学特性。车身侧部的两条贯穿前后的特征线是奥迪A7 Sportback的重要设计元素。一条特征线始于前大灯，穿过前翼子板、车门和后翼子板，轻轻地收于尾灯；另一条贯穿车身裙部，并上挑与尾灯和尾部上沿衔接在一起。这两条均衡完美的弧线，以及侧窗下沿线上挑的造型，赋予车身运动、优雅和轻快的视觉感受。\n平缓延伸至尾部的车顶轮廓线使C柱与后备厢融合在一起，这条轮廓线到达尾部后几乎呈垂直状陡然向下，勾勒出干净利落的车尾线条。不仅描绘出奥迪A7 Sportback曼妙的身形，还充分体现了运动与优雅的主题，并且袭承了奥迪100 Coupe S轿跑车唯美设计的精髓。\n一体化进气格栅\n奥迪标志性的一体化进气格栅赋予A7 Sportback不凡的气度，横向镀铬饰条和钢琴漆饰板穿插其间，闪现着极高的质感；大尺寸辅助进气口则强化了运动与高性能的视觉感受。\n商家名称：常州瑞达汽车销售服务有限公司\n销售电话：0519-81099888\n店面地址：常州市钟楼经济开发区棕榈路汽车城A区5号\n","url":"url:http://cz.auto.sina.com.cn/"},
        {"id":90,"title":"超越上市  新福美来/新福美来VS上市","time":"2017-03-18","content":"【新浪汽车讯】2012年4月11日，海马汽车开启福美来超越之旅，正式推出新福美来和新福美来VS两款新车。其中，新福美来VS是福美来品牌推出的首款两厢车型。目前，新车已全面登陆常州恒通海马4S店，并于4月15日常州地区上市，售价为7.68万-9.88万。同时，为配合新车上市，海马汽车针对福美来特别开启“春享行动——510购车方案”，为消费者提供更具性价比的优质产品。\n新福美来、新福美来VS车型及市场指导价\n新福美来\n风尚版\n手动\n7.68万\n自动\n8.88万\n精英版\n手动\n8.18万\n自动\n9.38万\n旗舰版\n手动\n8.68万\n自动\n9.88万\n新福美来VS\n风尚版\n手动\n7.68万\n自动\n8.88万\n精英版\n手动\n8.18万\n自动\n9.38万\n旗舰版\n手动\n8.68万\n自动\n9.88万\n作为中级轿车市场的开拓者，从合资车型到自主车型，再到全面超越的自主高端产品，福美来品牌，经历10年的洗礼，赢得了80万用户的赞誉。在福美来80万用户的共鉴之下，新福美来、新福美来VS两款新车依托福美来十年品牌积淀，三代的发展，传承品牌优秀基因，通过赛道检验，融入时尚元素，成为福美来品牌最重要的一大超越，打造引领市场的新基准中级家轿。\n春享行动——510购车方案——半价购车 + 千元升级 + 购车 0利率\n为回馈消费者，自上市之日起至5月31日，凡预定新福美来的消费者均可享受海马汽车春享行动“510”购车方案。享受半价购车，加1000元配置升级，贷款购车0利率的优惠。\n加入半价购车方案的消费者，在首付50%的基础上可享受“0月供，0利息，一年内还完余款”的金融支持；“千元升级”优惠计划即凡订购新福美来的客户只需加1000元，便可实现“风尚版到精英版，精英版到旗舰版”的配置升级；而在贷款购车0利率方案中，除了首付50%的0利率购车政策，海马汽车还针对首付为30%的消费者推出特别金融服务政策，消费者可以根据实际情况来选择一年期、两年期和三年期的购车贷款，其中一年期和两年期贷款所产生的利息完全不用消费者承担，三年期只需承担贷款额4.5%的利率。\n这一金融服务政策的推出，将大大减少消费者的购车经济负担，让福美来的性价比优势更为明显，在2012年紧凑型中级车市场中更具竞争力。\n安全、配置全面升级  动力、操控领先同级\n此次推出的福美来、新福美来VS在外观和配置上都有了很大的进步。\n新福美来和新福美来VS充分吸取收时下流行元素，采用全新欧系锐动式的“X”型一体化前脸，搭配锋锐动感的鹰眼式前大灯，给人强烈的视觉冲击，动感十足。而海马汽车首推的两厢新车——新福美来VS的双色尾灯绚丽动感，宛如“天使之翼”，更充分传递出车主年轻、时尚、追求品质的个性魅力。\n新福美来、新福美来VS整车内饰以德系标准精心打造，上黑下米的色调、细微之处的精致工艺、上乘的绿色环保材质，以及国际SGS无毒认证环保材质的车内塑料件，都体现出海马汽车消费者的贴心关怀。全系标配的多功能方向盘、自动防眩目内后视镜、胎压监测系统（风尚版除外）、倒车雷达、大灯高度可调功能（风尚版除外）、四安全气囊（风尚版除外）等也大大提高了新车人性化驾乘感及安全保证。\n两款上市车型均采用领先于同级车的RCBS赛车级轿跑底盘组合，大量汲取2011年CTCC夺冠战车的数据和经验，精心调校，并匹配前麦弗逊式、后多连杆4轮独立悬挂系统，以及胎面加宽轮胎，使驾驶更平稳，进一步提升了安全度、操控性。新福美来、新福美来VS采用海马汽车自主研发的HA-VVT-1.6发动机，最大功率88.2kw，最大扭矩158牛•米，并匹配5速手动或CVT无级变速器，实现了强劲动力和高效节油的完美平衡，在同级别车型中处于领先。而独有的智能化发动机控制模块(ECU)、VVT可变气门控制系统、闭环控制电喷系统、S-EFI 顺序多点燃油喷射系统等四大节油科技，为用户提供更高效，更节油的实惠用车生活。\n自上市之日起，海马汽车便发起了春享行动，让消费者得到更多的实惠，随后，海马汽车还将在全国开展“超越一刻”的店头试驾体验式营销，让消费者切身感受新车的操控性能。\n","url":"url:http://cz.auto.sina.com.cn/"},
        {"id":24,"title":"奥迪A1五门版将入华 售价较三门版提高","time":"2017-03-18","content":"【新浪汽车讯】在去年东京车展发布后，奥迪A1的五门版车型将在今年下半年引入中国市场。记者上周从知情人士处获悉，进口中国的五门版A1将与三门版一样配备1.4TFSI发动机，售价将小幅提高。\n在去年10月A1三门版车型进口之后，初期的销售表现并不尽如人意，为此一汽奥迪在去年底迅速调整营销策略，将此前的个性化定制策略改为根据大多数客户需求，生产已加配车型直接销售为主，同时价格也有松动。由于和进入中国多年且营销已较为深入的宝马Mini品牌存在直接竞争，仅有三门版的A1要迅速打开市场并不轻松。\n据了解，A1五门版的引入是一汽奥迪今年年初既定的产品策略，可以有效弥补三门版A1遗漏的市场空当。五门版A1最大的改变就是增加了两个后排车门，前后门比例做出些许调整。车身侧面B柱和C柱位置经过重新设计，车长和轴距与三门版相同，依然为3954mm和2469mm，不过宽度和高度都增加了6mm。动力方面，进口中国的将依然为1.4TFSI车型，最大功率为90kW。\n据了解，目前A1在终端销售层面以信贷购车以及置换补贴为主要促销手段，经销商表示，五门版比三门版更实用，性价比也会更高，引入有望有效提升A1的销量。\n","url":"url:http://cz.auto.sina.com.cn/"},
        {"id":3,"title":"title3","time":"2013-07-08","content":"原告王某诉称，母亲李某与继父周连志于××××年××月结婚时不满16周岁。其次，我一直与李某、周连志共同生活，一直在家干活，没有独立经济来源。已经形成继父女抚养关系，特别重要的是2009年周连志办理户口登记时明确承认王某系周连志长女。并且得到了公安机关的确认，因此，我是合法继承人。\n","url":"http://3"},
        {"id":58,"title":"新款别克昂科雷亮相纽约车展 今秋上市","time":"2017-03-18","content":"新款别克昂科雷亮相纽约车展 今秋上市\n【新浪汽车讯】别克昂科雷自2007年推出以来，销量持续攀升，过去两年在美国市场的年销量均在5万辆以上，成为最畅销的三排座跨界车。在今日开幕的纽约车展，别克带来了昂科雷中期改款版，新车将于今年秋季上市销售。\n新款别克昂科雷亮相纽约车展 今秋上市\n由于昂科雷深受欢迎，厂方无意对进行大刀阔斧的改动，只是在原有基础上进行小幅提升。新款昂科雷换上了新设计的车头大包围、前大灯和进气格栅，并加入两条LED日间行车灯，发动机盖也随之做出了一些修饰。车尾换上了LED尾灯，配以四边形大口径镀铬排气管。新设计的合金轮毂提供19英寸和20英寸两种选择，昂科雷依旧在车身上采用了大量的镀铬装饰提升豪华感，这也是别克的传统手法。\n新款别克昂科雷亮相纽约车展 今秋上市\n座舱内则融入了一些当下流行的设计元素，内饰用料得到了改善，触感更柔软，冰蓝色的LED氛围灯令座舱看上去更加宽敞。新款中控台配有Intellilink信息娱乐系统，三区自动恒温空调和导航系统。\n新款别克昂科雷亮相纽约车展 今秋上市\n在安全设备方面，别克昂科雷抢先配备了前排中央安全气囊，能够在侧面碰撞中避免驾驶员与副驾驶席乘客出现碰撞。与现款相同，安全性更强的新款也必定能够获得美国高速公路安全保险协会的最高安全评级。\n新款别克昂科雷亮相纽约车展 今秋上市\n为了提升新车的驾乘舒适性和操控性，新款昂科雷换上了新设计的减震筒。六前速自动变速箱经过改进后能够提供更舒适的动力传递，以及更接近轿车的操控性能。\n","url":"url:http://cz.auto.sina.com.cn/"},
        {"id":97,"title":"试驾全新宝马6系Gran Coupe 引擎性能(5)","time":"2017-03-18","content":"宝马6系\n不过真正令6系Gran Coupe与众不同的，还要当属它高度灵动的动态表现。从驾驶着的角度来看，流水一般的操控甚至超越了5系车型。转向部分，与6系其他车型相同的机电系统，力度得当、指向精准。你能感受到底盘的平衡表现，它急于攻弯，却又一直游走在极限的边缘，乐趣非同凡响。\n宝马6系\n6系Gran Coupe总共拥有5种驾驶模式，分别是超级节能、舒适+、舒适、运动以及运动+。你可以拥有广泛的自定义设置空间，包括调节转向、油门以及悬挂特性等等。\n驾驶起来如同绝大多数的宝马车型一样，车身稳固坚硬，但也能在舒适模式下得到足够的闲适感。即便是选装了18英寸防爆轮胎，也不会破坏该有的舒适。高速公路上，是岩石般坚固的稳定性兼具巡航状态下的些许放松，它是可以信赖的长途伙伴。\n宝马6系\n总的来说，6系Gran Coupe有着众多优点，它比5系更好看也更能激发人的驾驶欲望，即便是没有同级别的实用性和装载能力。与6系Coupe和敞篷相似的运动风格内饰，在路途中依旧让人欢喜。像奔驰CLS和奥迪A7一样，这是一款更适合性格外向的人的座驾，单就北美市场来说，这里正是它们的福地。从某种角度来说，宝马6系Gran Coupe已经拥有了可以预见的成功，虽然入门级的640i Gran Coupe起始价也要76,895美元。\n","url":"url:http://cz.auto.sina.com.cn/"}]}


    return strHtml1;
}


function getName(id){
    return document.getElementById(id);
} //定义获取ID的方法

function GotoPage(num){ //跳转页
    Page = num;
    OutputHtml();
}

var PageSize = 4; //每页个数
var Page = 1; //当前页码


function OutputHtml(){
    var strurl="http://localhost:8080/getJson?query=";
    strurl +=  $("#strquery").val();
//    strurl += "&callback=?";
    console.log(strurl);

    $.getJSON(strurl,function(data){

        var obj = eval(data);   //获取JSON
        var rows = obj.rows;

        //获取分页总数
        var Pages = Math.floor((rows.length - 1) / PageSize) + 1;
        if(Page < 1)Page = 1;  //如果当前页码小于1
        if(Page > Pages)Page = Pages; //如果当前页码大于总数
        //var Temp = ""''
        var Temp = "<ul class='pagination'>";

        var BeginNO = (Page - 1) * PageSize + 1; //开始编号
        var EndNO = Page * PageSize; //结束编号
        if(EndNO > rows.length) EndNO = rows.length;
        if(EndNO == 0) BeginNO = 0;

        if(!(Page <= Pages)) Page = Pages;
//    getName("total").innerHTML = "Total:<strong class='f90'>" + rows.length + "</strong>&nbsp;&nbsp;Show:<strong class='f90'>" + BeginNO + "-" + EndNO + "</strong>";

        //分页
        if(Page > 1 && Page !== 1){
            Temp +="<li> <a href='javascript:void(0)' onclick='GotoPage(1)'>Index</a> </li>" +
                "<li> <a href='javascript:void(0)' onclick='GotoPage(" + (Page - 1) + ")'>&lt;&lt;</a> </li>&nbsp;"
        }else{
            Temp += "<li><a>Index</a></li> "
        };

        //完美的翻页列表
        var PageFrontSum = 10; //当页前显示个数
        var PageBackSum = 3; //当页后显示个数

        var PageFront = PageFrontSum - (Page - 1);
        var PageBack = PageBackSum - (Pages - Page);
        if(PageFront > 0 && PageBack < 0)PageBackSum += PageFront; //前少后多，前剩余空位给后
        if(PageBack > 0 && PageFront < 0)PageFrontSum += PageBack; //后少前多，后剩余空位给前
        var PageFrontBegin = Page - PageFrontSum;
        if(PageFrontBegin < 1)PageFrontBegin = 1;
        var PageFrontEnd = Page + PageBackSum;
        if(PageFrontEnd > Pages)PageFrontEnd = Pages;

        if(PageFrontBegin != 1)
            Temp += '<li> <a href="javascript:void(0)" onclick="GotoPage('
                + (Page - 10) + ')" title="前10页">..</a> </li>';
        for(var i = PageFrontBegin;i < Page;i ++){
            Temp += "<li> <a href='javascript:void(0)' onclick='GotoPage("
                + i + ")'>" + i + "</a> </li>";
        }

        Temp += "<li><a href='#'> <strong class='f90'>" + Page + "</strong></a> </li>";
        for(var i = Page + 1;i <= PageFrontEnd;i ++){
            Temp += "<li> <a href='javascript:void(0)' onclick='GotoPage(" + i + ")'>" + i + "</a> </li>";
        }
        if(PageFrontEnd != Pages)
            Temp += "<li> <a href='javascript:void(0)' onclick='GotoPage(" + (Page + 10)
                + ")' title='后10页'>..</a> </li>";

        if(Page != Pages){
            Temp += "&nbsp;&nbsp;<li>  <a href='javascript:void(0)' onclick='GotoPage("
                + (Page + 1) + ");'> &gt;&gt; </a></li> <li> <a href='javascript:void(0)' onclick='GotoPage("
                + Pages + ")'>Last</a> </li>"
            /*Temp += "&nbsp;&nbsp;<span> <a href='javascript:void(0)' onclick='GotoPage("
                + (Page + 1) + ");'>Next</a> <a href='javascript:void(0)' onclick='GotoPage("
                + Pages + ")'>Last&gt;&gt;</a> </span>"*/
        }else{
            Temp += "<li><a> Last </a></li> "
        }
        Temp += "</ul>";

        getName("pagelist").innerHTML = Temp;
    //    console.log(Temp);



        //输出数据

        if(EndNO == 0){ //如果为空
            getName("content").innerHTML += "<h1>No Found</h1>";
            return;
        }

        var html = "";
        for(var i = BeginNO - 1; i < EndNO; i++) {
            html += "<div class='item'>";
            html += "<h4> <a href='" + rows[i].url +"' target='_blank'>" + rows[i].title + "</a> </h4>";
            var str = rows[i].content;
            var textLeng = 100;
            if(str.length > textLeng ){
                rows[i].content = str.substring(0,textLeng )+"...";
            }
            html += "<div class='txt' id='txt'>" + rows[i].time + " - " + rows[i].content + "</div>";
            html += "<p> <a href='" + rows[i].url + "'>" + rows[i].url + "</a> </p>";
//            html += "<p>" + rows[i].time + " - " + "<span id=" + "txt" +">" + rows[i].content
//                    + "</span> </br> <a href='" + rows[i].url + "'>" + rows[i].url + "</a> </p>" ;
            html += "</div>";
            html += "<br>";
            html += "<br>";
        }

        getName("content").innerHTML = html;
        clickShow(); //调用鼠标点击事件



        //键盘左右键翻页
        document.onkeydown=function(e){
            var theEvent = window.event || e;
            var code = theEvent.keyCode || theEvent.which;
            if(code==37){
                if(Page > 1 && Page !== 1){
                    GotoPage(Page - 1);

                }
            }
            if(code==39){
                if(Page != Pages){
                    GotoPage(Page + 1);
                }
            }
        }

    });

    /*
     $.ajax({
     url: strurl ,
     dataType: "jsonp",
     contentType: "application/json; charset=utf-8",
     success: function(data) {
     var obj = eval(data);   //获取JSON
     var rows = obj.rows;

     //获取分页总数
     var Pages = Math.floor((rows.length - 1) / PageSize) + 1;
     if(Page < 1)Page = 1;  //如果当前页码小于1
     if(Page > Pages)Page = Pages; //如果当前页码大于总数
     //var Temp = ""''
     var Temp = "<ul class='pagination'>";

     var BeginNO = (Page - 1) * PageSize + 1; //开始编号
     var EndNO = Page * PageSize; //结束编号
     if(EndNO > rows.length) EndNO = rows.length;
     if(EndNO == 0) BeginNO = 0;

     if(!(Page <= Pages)) Page = Pages;
     //    getName("total").innerHTML = "Total:<strong class='f90'>" + rows.length + "</strong>&nbsp;&nbsp;Show:<strong class='f90'>" + BeginNO + "-" + EndNO + "</strong>";

     //分页
     if(Page > 1 && Page !== 1){
     Temp +="<li> <a href='javascript:void(0)' onclick='GotoPage(1)'>Index</a> </li>" +
     "<li> <a href='javascript:void(0)' onclick='GotoPage(" + (Page - 1) + ")'>&lt;&lt;</a> </li>&nbsp;"
     }else{
     Temp += "<li><a>Index</a></li> "
     };

     //完美的翻页列表
     var PageFrontSum = 3; //当页前显示个数
     var PageBackSum = 3; //当页后显示个数

     var PageFront = PageFrontSum - (Page - 1);
     var PageBack = PageBackSum - (Pages - Page);
     if(PageFront > 0 && PageBack < 0)PageBackSum += PageFront; //前少后多，前剩余空位给后
     if(PageBack > 0 && PageFront < 0)PageFrontSum += PageBack; //后少前多，后剩余空位给前
     var PageFrontBegin = Page - PageFrontSum;
     if(PageFrontBegin < 1)PageFrontBegin = 1;
     var PageFrontEnd = Page + PageBackSum;
     if(PageFrontEnd > Pages)PageFrontEnd = Pages;

     if(PageFrontBegin != 1)
     Temp += '<li> <a href="javascript:void(0)" onclick="GotoPage('
     + (Page - 10) + ')" title="前10页">..</a> </li>';
     for(var i = PageFrontBegin;i < Page;i ++){
     Temp += "<li> <a href='javascript:void(0)' onclick='GotoPage("
     + i + ")'>" + i + "</a> </li>";
     }

     Temp += "<li><a href='#'> <strong class='f90'>" + Page + "</strong></a> </li>";
     for(var i = Page + 1;i <= PageFrontEnd;i ++){
     Temp += "<li> <a href='javascript:void(0)' onclick='GotoPage(" + i + ")'>" + i + "</a> </li>";
     }
     if(PageFrontEnd != Pages)
     Temp += "<li> <a href='javascript:void(0)' onclick='GotoPage(" + (Page + 10)
     + ")' title='后10页'>..</a> </li>";

     if(Page != Pages){
     Temp += "&nbsp;&nbsp;<li>  <a href='javascript:void(0)' onclick='GotoPage("
     + (Page + 1) + ");'> &gt;&gt; </a></li> <li> <a href='javascript:void(0)' onclick='GotoPage("
     + Pages + ")'>Last</a> </li>"
     /!*Temp += "&nbsp;&nbsp;<span> <a href='javascript:void(0)' onclick='GotoPage("
     + (Page + 1) + ");'>Next</a> <a href='javascript:void(0)' onclick='GotoPage("
     + Pages + ")'>Last&gt;&gt;</a> </span>"*!/
     }else{
     Temp += "<li><a> Last </a></li> "
     }
     Temp += "</ul>";

     getName("pagelist").innerHTML = Temp;
     //    console.log(Temp);



     //输出数据

     if(EndNO == 0){ //如果为空
     getName("content").innerHTML += "<h1>No Found</h1>";
     return;
     }

     var html = "";
     for(var i = BeginNO - 1; i < EndNO; i++) {
     html += "<div class='item'>";
     html += "<h4> <a href='" + rows[i].url +"' target='_blank'>" + rows[i].title + "</a> </h4>";
     var str = rows[i].content;
     var textLeng = 100;
     if(str.length > textLeng ){
     rows[i].content = str.substring(0,textLeng )+"...";
     }
     html += "<div class='txt' id='txt'>" + rows[i].time + " - " + rows[i].content + "</div>";
     html += "<p> <a href='" + rows[i].url + "'>" + rows[i].url + "</a> </p>";
     //            html += "<p>" + rows[i].time + " - " + "<span id=" + "txt" +">" + rows[i].content
     //                    + "</span> </br> <a href='" + rows[i].url + "'>" + rows[i].url + "</a> </p>" ;
     html += "</div>";
     html += "<br>";
     html += "<br>";
     }

     getName("content").innerHTML = html;
     clickShow(); //调用鼠标点击事件



     //键盘左右键翻页
     document.onkeydown=function(e){
     var theEvent = window.event || e;
     var code = theEvent.keyCode || theEvent.which;
     if(code==37){
     if(Page > 1 && Page !== 1){
     GotoPage(Page - 1);

     }
     }
     if(code==39){
     if(Page != Pages){
     GotoPage(Page + 1);
     }
     }
     }


     }
     });
     */
}

//获取链接地址和网站名称
function showLink(source){
    var siteUrl = getName("siteurl");
    var siteName = getName("sitename");
    var description = getName("description");

    if(source.getAttribute("rel") == "bookmark"){
        var url = source.getAttribute("href");
        var title = source.getAttribute("title");
        siteUrl.innerHTML = "<a href='" + url + "' target='_blank'>"+ url +"</a>";
        siteName.innerHTML = title;
    }

}

//鼠标点击事件
function clickShow(){
    var links = getName("content").getElementsByTagName("a");
    for(var i=0; i<links.length; i++){
        var url = links[i].getAttribute("href");
        var title = links[i].getAttribute("title");
        links[i].onclick = function(){
            showLink(this);
            return false;
        }
    }
}