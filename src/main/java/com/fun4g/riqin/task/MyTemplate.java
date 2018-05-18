package com.fun4g.riqin.task;

/**
 * Created by scottsun on 2016/6/30.
 */
public class MyTemplate {
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String touser;
    private String template_id;
    private String url;
    private String first;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String keyword4;
    private String keyword5;

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    private String work;
    private String remark;
    @Override
    public String toString(){
        String temp="{\n" +
                "\"touser\":\""+touser+"\",\n" +
                "\"template_id\":\""+template_id+"\",\n" +
                "\"url\":\""+url+"\",\n" +
                "\"topcolor\":\"#FF0000\",\n" +
                "\"data\":{\n" +
                "\"first\": {\n" +
                "\"value\":\""+first+"\",\n" +
                "\"color\":\"#173177\"\n" +
                "},\n" +
                "\"keyword1\": {\n" +
                "\"value\":\""+keyword1+"\",\n" +
                "\"color\":\"#173177\"\n" +
                "},\n" ;
        if(keyword2!=null&&!keyword2.trim().equals("")){
            temp=temp+"\"keyword2\": {\n" +
                    "\"value\":\""+keyword2+"\",\n" +
                    "\"color\":\"#173177\"\n" +
                    "},\n";
        }
        if(keyword3!=null&&!keyword3.trim().equals("")){
            temp=temp+"\"keyword3\": {\n" +
                    "\"value\":\""+keyword3+"\",\n" +
                    "\"color\":\"#173177\"\n" +
                    "},\n";
        }
        if(keyword4!=null&&!keyword4.trim().equals("")){
            temp=temp+"\"keyword4\": {\n" +
                    "\"value\":\""+keyword4+"\",\n" +
                    "\"color\":\"#173177\"\n" +
                    "},\n";
        }
        if(keyword5!=null&&!keyword5.trim().equals("")){
            temp=temp+"\"keyword2\": {\n" +
                    "\"value\":\""+keyword5+"\",\n" +
                    "\"color\":\"#173177\"\n" +
                    "},\n";
        }
        if(work!=null&&!work.trim().equals("")){
            temp=temp+"\"work\": {\n" +
                    "\"value\":\""+work+"\",\n" +
                    "\"color\":\"#173177\"\n" +
                    "},\n";
        }
        temp=temp+
                "\"remark\": {\n" +
                "\"value\":\""+remark+"\",\n" +
                "\"color\":\"#173177\"\n" +
                "}" +
                "}}";

        return temp;
    }

}
