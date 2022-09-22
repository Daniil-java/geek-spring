package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Banana", 10));
        this.productRepository.insert(new Product("AnotherBanana", 10));
        this.productRepository.insert(new Product("Apple", 10));
        this.productRepository.insert(new Product("Orange", 10));
        this.productRepository.insert(new Product("Juice", 10));
        this.productRepository.insert(new Product("Water", 10));
        this.productRepository.insert(new Product("Something", 10));
        this.productRepository.insert(new Product("Spam", 10));
        this.productRepository.insert(new Product("Eggs", 10));
        this.productRepository.insert(new Product("Coke", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Username</th>");
        wr.println("</tr>");

        if (req.getQueryString() == null) {
            for(Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td><a href='?id=" + product.getId() +"'>" + product.getId() +"</a></td>");
                wr.println("<td>" + product.getName() + "</td>" );
                wr.println("</tr>");
            }
        }

        if(!req.getParameter("id").equals(null)) {
            wr.println("<tr>");
            wr.println("<td>" + req.getParameter("id") +"</td>");
            wr.println("<td>" + productRepository.findById(Long.parseLong(req.getParameter("id"))).getName() + "</td>");
            wr.println("</tr>");
        }

        wr.println("</table>");
    }

}