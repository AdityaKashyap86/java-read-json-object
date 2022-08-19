package com.sunglowsys.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunglowsys.domain.Employee;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ReadDataJsonFile {
    public static void main(String[] args) throws Exception {

        String json =  FileUtils.readFileToString (new File ("D:\\json\\employees.json"), StandardCharsets.UTF_8);
        System.out.println (json);

        ObjectMapper objectMapper = new ObjectMapper ();
        Employee  employee = objectMapper.readValue (json,Employee.class);
        System.out.println (employee);

        Configuration configuration = new Configuration ().configure ();
        SessionFactory sessionFactory = configuration.buildSessionFactory ();
        Session session = sessionFactory.openSession ();
        Transaction transaction = session.beginTransaction ();

        session.save (employee);
        transaction.commit ();
        session.close ();
        sessionFactory.close ();
    }
}



