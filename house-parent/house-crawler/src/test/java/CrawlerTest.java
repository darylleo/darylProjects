import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author wl
 * @create 2022-02-12
 */
@SpringBootTest
public class CrawlerTest {

    @Test
    public void test01() {
        Spider.create(new HouseProcessor())
                //.addUrl("https://nb.58.com/chuzu/?PGTID=0d3090a7-0267-e9dc-2967-378c758a39d4&ClickID=1")
                .addUrl("https://nb.58.com/zufang/49258966603532x.shtml?houseId=2383106040933384&shangquan=zhaobaoshan&shangquanId=10693&dataSource=2&tid=9c76721e-0cc4-4a91-9a51-70633b5df35f&legourl=//legoclick.58.com/jump?target=szq8mB3draOWUvYfXMRhmyOMs1EOnWN3rHmvPWT1PHnzXaO1pZwVUT7bsynzmWK6nHwbsHuBPjbVPjR-rid6mymYsHmdPH7-PvEkn1c3mkD1PHmvn1DLnHDzP19znkDKnWn3n1DkPWTYnjb1n1n3PTDvPWTkTHmvnjTKnikQnTDQn1NKnHmYPjbLrjndrjEvnTDQTNujsRKjsXXMMSpcfSi38Syc-SB6Jrh6VXpRVryEBrXMMSpLVSprb9DQTHDKTiYQTEDQPjb1nHbznjE3PWTknHmzn1TYTHmKTHDKPHPBmHbdP1nVrAckPaYYrHw-sH9krAnVPHubmvwBPvnYnHR-THDYrHnQrHckPj9Ln19dP19Yn1cKnHEOn1DOnWTYrjmznHD1P1bznEDKnikQnTDKTEDVTEDKTR6wwvdbUN-cHZCQINwoINdW07uriv0q5EDQnWc8nWEdsWcQPz3Yn9DkTHTKTHD_nHDKXLYKnHTkn1nKnHTknHNkP9DOm10vP1cQuiYkmvnYsHw6rHDVryDdniYLnjm1nvcduAm1PymKTHTKTEDkTHD1PikQn1b_nHTvrHnKnE78IyQ_THKBmHmLm176PAc1m1E3mhc&iuType=gz_1&PGTID=0d3090a7-0008-7154-bc6d-1d4457a990b5&ClickID=2")
                .run();
    }

    @Test
    public void jsoupTest(){
        File file = new File("C:\\Users\\Daryl\\Desktop\\house.html");
        try {
            Document parse = Jsoup.parse(file, "utf-8");
            System.out.println(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
