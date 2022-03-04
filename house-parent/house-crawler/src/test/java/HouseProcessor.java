import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author wl
 * @create 2022-02-12
 */
public class HouseProcessor implements PageProcessor {
    @Override
    public void process(Page page) {

        List<Selectable> sss = page.getHtml().css("ul#leftImg").nodes();
        //String src = (String) img;
/*        for (String s : src) {
            System.out.println(s);
        }*/

        System.out.println(889898);
        //解析页面，获取房源详情的url地址
        //page.putField("div", page.getHtml().css("div.list-box ul.house-list li.house-cell div.img-list a","href").all());
        List<Selectable> nodes = page.getHtml().css("div.list-box ul.house-list li.house-cell").nodes();
        //System.out.println(nodes);
        //判断解析到的结合是否为空
        if (nodes.size() == 0) {
            //如果为空，为房源详情页

        } else {
            //如果不为空，为列表页
            for (Selectable node : nodes) {
                //获取url地址
                String houseInfoUrl = node.links().toString();
                //System.out.println(houseInfoUrl);
                //把获取到的url地址加入到任务队列中
                page.addTargetRequest(houseInfoUrl);
            }
            String href = page.getHtml().css("div.list-box ul.house-list div.pager a", "href").get();
            page.addTargetRequest(href);
        }


    }

    private void saveContent(Page page) {
        Html html = page.getHtml();
        String title = html.css("div.house-title h1").toString();
        String price = Jsoup.parse(html.css("div.house-pay-way.f16 span.c_ff552e ").toString()).text();
        String paymentStandard = html.css("div.house-pay-way.f16 span.instructions").toString();
        List<Selectable> nodes = html.css("div.house-desc-item.fl.c_333 ul.f14 li").nodes();
        html.css("div.basic-pic-list.pr li","src").nodes();
        String leaseMode = Jsoup.parse(nodes.get(0).toString()).text();
        String houseType = Jsoup.parse(nodes.get(1).toString()).text();
        String towardsFloor = Jsoup.parse(nodes.get(2).toString()).text();
        String community = Jsoup.parse(nodes.get(3).toString()).text();
        String region = Jsoup.parse(nodes.get(4).toString()).text();
        String detailedAddress = Jsoup.parse(nodes.get(5).toString()).text();
        //String region = Jsoup.parse(nodes.get(4).toString()).text();
        System.out.println(33);
    }

    private Site site = Site.me();

    @Override
    public Site getSite() {
        return site;
    }
}
