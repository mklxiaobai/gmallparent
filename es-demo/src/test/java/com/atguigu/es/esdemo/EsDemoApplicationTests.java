package com.atguigu.es.esdemo;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDemoApplicationTests {

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() {
        System.out.println(jestClient);
    }

    @Test
    public void contextLoad3s() {
        //1、给Es中索引（保存）一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("java入门到放弃");
        article.setAuthor("jiatp");
        article.setContent("测试内容");

        //2、构建一个索引功能 type(类型名字)
        Index build = new Index.Builder(article).index("jiatp").type("news").build();
        try {
            //3、执行
            jestClient.execute(build);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void index() throws IOException {

        //Update  Delete Search Index
        //1、Action action = new Crud.Builder(携带的数据).其他信息.build();
        //2、DocumentResult execute = jestClient.execute(action);
        //3、获取结果DocumentResult数据即可

        User user = new User();
        user.setEmail("122333");user.setUserName("dsadasda");


        Index index = new Index.Builder(user)
                .index("user")
                .type("info")
                .build();

        DocumentResult execute = jestClient.execute(index);

        System.out.println("执行？"+execute.getId()+"==>"+execute.isSucceeded());
    }


    @Test
    public void query() throws IOException {

        //{\"query\":\"{\"match_all\":\"{}\"}\"}
        String queryJson ="";


        Search search = new Search.Builder(queryJson).addIndex("user").build();

        SearchResult execute =
                jestClient.execute(search);

        System.out.println("总记录"+execute.getTotal()+"==>最大得分："+execute.getMaxScore());
        System.out.println("查到的数据");
        List<SearchResult.Hit<User, Void>> hits = execute.getHits(User.class);
        hits.forEach((hit)->{
                 User u = hit.source;
            System.out.println(u.getEmail()+"==>"+u.getUserName());
        });

    }



}

class User{
    private String userName;
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
class Article{
    private int id;
    private String title;
    private String author;
    private String content;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}