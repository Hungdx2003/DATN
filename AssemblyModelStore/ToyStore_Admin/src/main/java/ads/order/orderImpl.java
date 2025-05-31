package ads.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.OrderObject;

public class orderImpl extends basicImpl implements order {

	public orderImpl() {
		super("Order");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addOrder(OrderObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO orders(");
		sql.append("user_id, order_date, receiver_name, receiver_mobilephone, ");
		sql.append("delivery_address, order_status, total_amount)");
		sql.append("VALUES(?,?,?,?,?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getUser_id());
            pre.setString(2, item.getOrder_date());
            pre.setString(3, item.getReceiver_name());
            pre.setString(4, item.getReceiver_mobilephone());
            pre.setString(5, item.getDelivery_address());
            pre.setString(6, item.getOrder_status());
            pre.setInt(7, item.getTotal_amount());

            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editOrder(OrderObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE orders SET ");
		sql.append("order_status=? ");
		sql.append("WHERE order_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setString(1, item.getOrder_status());
            pre.setInt(2, item.getOrder_id());
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public ArrayList<ResultSet> getOrder(OrderObject similar, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM orders ");
		sql.append("");
		sql.append("ORDER BY order_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getOrder(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM orders WHERE order_id=?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> countOrder(OrderObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS daily_order_count FROM orders WHERE DATE(order_date) = CURDATE(); ");

		sql.append("SELECT COUNT(*) AS monthly_order_count FROM orders WHERE YEAR(order_date) = YEAR(CURDATE()) ")
	         .append("AND MONTH(order_date) = MONTH(CURDATE()); ");

		sql.append("SELECT COUNT(*) AS yearly_order_count FROM orders WHERE YEAR(order_date) = YEAR(CURDATE());");
		
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> countQuantity(OrderObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();


		sql.append("SELECT SUM(od.quantity) AS daily_quantity_sold ")
	         .append("FROM orders o ")
	         .append("JOIN orderdetails od ON o.order_id = od.order_id ")
	         .append("JOIN payments p ON o.order_id = p.order_id ")
	         .append("WHERE DATE(o.order_date) = CURDATE() AND p.payment_status = 'Đã thanh toán'; ");

		sql.append("SELECT SUM(od.quantity) AS monthly_quantity_sold ")
	         .append("FROM orders o ")
	         .append("JOIN orderdetails od ON o.order_id = od.order_id ")
	         .append("JOIN payments p ON o.order_id = p.order_id ")
	         .append("WHERE YEAR(o.order_date) = YEAR(CURDATE()) ")
	         .append("  AND MONTH(o.order_date) = MONTH(CURDATE()) ")
	         .append("  AND p.payment_status = 'Đã thanh toán'; ");

		sql.append("SELECT SUM(od.quantity) AS yearly_quantity_sold ")
	         .append("FROM orders o ")
	         .append("JOIN orderdetails od ON o.order_id = od.order_id ")
	         .append("JOIN payments p ON o.order_id = p.order_id ")
	         .append("WHERE YEAR(o.order_date) = YEAR(CURDATE()) ")
	         .append("  AND p.payment_status = 'Đã thanh toán';");
		
		return this.gets(sql.toString());
	}

	@Override
	public ArrayList<ResultSet> getRevenue(OrderObject similar) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT DATE(o.order_date) AS order_day, SUM(o.total_amount) AS daily_revenue ");
		sql.append("FROM orders o ");
		sql.append("JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE p.payment_status = 'đã thanh toán' ");
		sql.append("AND YEAR(o.order_date) = YEAR(CURDATE()) ");
		sql.append("AND MONTH(o.order_date) = MONTH(CURDATE()) ");
		sql.append("GROUP BY DATE(o.order_date) ");
		sql.append("ORDER BY DATE(o.order_date); ");

        // Tổng doanh thu theo tháng trong năm nay
		sql.append("SELECT MONTH(o.order_date) AS order_month, SUM(o.total_amount) AS monthly_revenue ");
		sql.append("FROM orders o ");
		sql.append("JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE p.payment_status = 'đã thanh toán' ");
		sql.append("AND YEAR(o.order_date) = YEAR(CURDATE()) ");
		sql.append("GROUP BY MONTH(o.order_date) ");
		sql.append("ORDER BY MONTH(o.order_date); ");

        // Tổng doanh thu theo các năm
		sql.append("SELECT YEAR(o.order_date) AS order_year, SUM(o.total_amount) AS yearly_revenue ");
		sql.append("FROM orders o ");
		sql.append("JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE p.payment_status = 'đã thanh toán' ");
		sql.append("GROUP BY YEAR(o.order_date) ");
		sql.append("ORDER BY YEAR(o.order_date);");
		
		return this.gets(sql.toString());
	}

}
