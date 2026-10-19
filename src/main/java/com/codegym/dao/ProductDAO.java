package com.codegym.dao;
import com.codegym.model.Product;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
public class ProductDAO {
 private Connection connect() throws SQLException {
  String url=System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3306/product_management?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh");
  String user=System.getenv().getOrDefault("DB_USER","root");
  String pass=System.getenv().getOrDefault("DB_PASSWORD","");
  return DriverManager.getConnection(url,user,pass);
 }
 private Product map(ResultSet rs)throws SQLException{return new Product(rs.getInt("id"),rs.getString("name"),rs.getBigDecimal("price"),rs.getInt("quantity"));}
 public List<Product> findAll()throws SQLException{
  List<Product> products=new ArrayList<>();
  try(Connection c=connect();PreparedStatement s=c.prepareStatement("SELECT id,name,price,quantity FROM products ORDER BY id");ResultSet rs=s.executeQuery()){
   while(rs.next())products.add(map(rs));
  }return products;
 }
 public Product findById(int id)throws SQLException{
  try(Connection c=connect();PreparedStatement s=c.prepareStatement("SELECT id,name,price,quantity FROM products WHERE id=?")){
   s.setInt(1,id);try(ResultSet rs=s.executeQuery()){return rs.next()?map(rs):null;}
  }
 }
 public void create(String name,BigDecimal price,int quantity)throws SQLException{
  try(Connection c=connect();PreparedStatement s=c.prepareStatement("INSERT INTO products(name,price,quantity) VALUES(?,?,?)")){
   s.setString(1,name);s.setBigDecimal(2,price);s.setInt(3,quantity);s.executeUpdate();
  }
 }
 public boolean update(int id,String name,BigDecimal price,int quantity)throws SQLException{
  try(Connection c=connect();PreparedStatement s=c.prepareStatement("UPDATE products SET name=?,price=?,quantity=? WHERE id=?")){
   s.setString(1,name);s.setBigDecimal(2,price);s.setInt(3,quantity);s.setInt(4,id);return s.executeUpdate()>0;
  }
 }
 public boolean delete(int id)throws SQLException{
  try(Connection c=connect();PreparedStatement s=c.prepareStatement("DELETE FROM products WHERE id=?")){
   s.setInt(1,id);return s.executeUpdate()>0;
  }
}