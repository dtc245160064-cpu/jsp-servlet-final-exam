package com.codegym.web;
import com.codegym.dao.ProductDAO;
import com.codegym.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
 private final ProductDAO dao=new ProductDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  try{
   String action=req.getParameter("action");
   if("new".equals(action)){req.setAttribute("mode","new");req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req,resp);return;}
   if("edit".equals(action)){
    Product p=dao.findById(Integer.parseInt(req.getParameter("id")));
    if(p==null){resp.sendError(404,"Product not found");return;}
    req.setAttribute("product",p);req.setAttribute("mode","edit");req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req,resp);return;
   }
   req.setAttribute("products",dao.findAll());req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req,resp);
  }catch(NumberFormatException e){resp.sendError(400,"Invalid product ID");}
   catch(SQLException e){throw new ServletException("Database error",e);}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8");
  String action=req.getParameter("action");
  try{
   if("delete".equals(action)){
    dao.delete(Integer.parseInt(req.getParameter("id")));
   }else if("create".equals(action)||"update".equals(action)){
    String name=req.getParameter("name");
    if(name==null||name.isBlank()||name.length()>255)throw new IllegalArgumentException("Ten san pham khong hop le");
    BigDecimal price=new BigDecimal(req.getParameter("price"));
    int quantity=Integer.parseInt(req.getParameter("quantity"));
    if(price.signum()<0||price.scale()>2||price.compareTo(new BigDecimal("9999999999.99"))>0||quantity<0)throw new IllegalArgumentException("Gia hoac so luong khong hop le");
    if("create".equals(action))dao.create(name.trim(),price,quantity);
    else dao.update(Integer.parseInt(req.getParameter("id")),name.trim(),price,quantity);
   }else{resp.sendError(400,"Invalid action");return;}
   resp.sendRedirect(req.getContextPath()+"/products");
  }catch(IllegalArgumentException e){
   resp.sendError(400,"Du lieu khong hop le: "+e.getMessage());
  }catch(SQLException e){throw new ServletException("Database error",e);}
 }
}