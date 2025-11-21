package org.example.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JakartaFreemarkerServlet extends HttpServlet {

    private Configuration freemarkerConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_34);
        // Загружаем шаблоны из корня webapp/
        freemarkerConfig.setServletContextForTemplateLoading(
                config.getServletContext(),
                "/"
        );
        freemarkerConfig.setDefaultEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        Map<String, Object> data = new HashMap<>();
        Enumeration<String> attrNames = req.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            data.put(name, req.getAttribute(name));
        }

        try {
            Template template = freemarkerConfig.getTemplate(path.substring(1)); // убираем "/"
            resp.setContentType("text/html; charset=UTF-8");
            template.process(data, resp.getWriter());
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}