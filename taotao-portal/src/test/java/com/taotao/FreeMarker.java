package com.taotao;

import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Title:com.taotao</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/12.
 */
public class FreeMarker {

    public class Student{
        private int id;
        private String name;
        private String address;

        public Student(int id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    @Test
    public void testFreeMarker() throws Exception{
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("H:\\javasefuxi\\Taotao01\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("first.ftl");
        HashMap<Object, Object> root = new HashMap<>();
        root.put("hello", "hello freemarker");
        Writer out = new FileWriter(new File("D:\\temp\\html\\hello.html"));
        template.process(root, out);
        out.flush();
        out.close();
    }

    @Test
    public void testFreeMarker2() throws Exception{
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("H:\\javasefuxi\\Taotao01\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("second.ftl");
        HashMap<Object, Object> root = new HashMap<>();
        root.put("title", "hello freemarker");
        root.put("student", new Student(1,"we外网","NewYork"));
        Writer out = new FileWriter(new File("D:\\temp\\html\\student.html"));
        template.process(root, out);
        out.flush();
        out.close();
    }

    @Test
    public void testFreeMarker3() throws Exception{
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("H:\\javasefuxi\\Taotao01\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("UTF-8");
        Template template = configuration.getTemplate("second.ftl");
        HashMap<Object, Object> root = new HashMap<>();
        root.put("title", "hello freemarker");
        root.put("student", new Student(1,"we外网","NewYork"));
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "jack1", "yancheng"));
        students.add(new Student(1, "jack12", "yancheng1"));
        students.add(new Student(1, "jack13", "yancheng2"));
        students.add(new Student(1, "jack14", "yancheng3"));
        students.add(new Student(1, "jack15", "yancheng4"));
        students.add(new Student(1, "jack16", "yancheng5"));
        root.put("students", students);
       // root.put("cur_date", new Date());
        Writer out = new FileWriter(new File("D:\\temp\\html\\student.html"));

        template.process(root, out);
        out.flush();
        out.close();
    }
}
