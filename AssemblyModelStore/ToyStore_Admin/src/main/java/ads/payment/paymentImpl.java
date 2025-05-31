package ads.payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.PaymentObject;

public class paymentImpl extends basicImpl implements payment {

	public paymentImpl() {
		super("Payment");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addPayment(PaymentObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO payments(");
		sql.append("order_id, payment_method, payment_status)");
		sql.append("VALUES(?,?,?)");
        try {
            PreparedStatement pre = this.con.prepareStatement(sql.toString());
            pre.setInt(1, item.getOrder_id());
            pre.setString(2, item.getPayment_method());
            pre.setString(3, item.getPayment_status());

            return this.add(pre);  
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean editPayment(PaymentObject item) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE payments SET ");
		sql.append("payment_method=?, payment_status=? ");
		sql.append("WHERE payment_id=?");
		try{
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
        	pre.setInt(1, item.getOrder_id());
            pre.setString(2, item.getPayment_method());
            pre.setString(3, item.getPayment_status());
            pre.setInt(4, item.getPayment_id());
            
            return this.edit(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public ArrayList<ResultSet> getPayment(int id, int at, byte total) {
		// TODO Auto-generated method stub
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT * FROM payments ");
		sql.append("WHERE user_id=").append(id);
		sql.append("ORDER BY order_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		
		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getPayment(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM payments WHERE order_id = ?";
		return this.get(sql, id);
	}

	@Override
	public ArrayList<ResultSet> getRevenueData(PaymentObject object) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(o.total_amount) AS daily_revenue FROM orders o ");
		sql.append("JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE p.payment_status = 'đã thanh toán' AND DATE(o.order_date) = CURDATE(); ");
             
		sql.append("SELECT SUM(o.total_amount) AS monthly_revenue FROM orders o ");
		sql.append("JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE p.payment_status = 'đã thanh toán' AND YEAR(o.order_date) = YEAR(CURDATE()) ");
		sql.append("AND MONTH(o.order_date) = MONTH(CURDATE()); ");
             
		sql.append("SELECT SUM(o.total_amount) AS yearly_revenue FROM orders o ");
		sql.append("JOIN payments p ON o.order_id = p.order_id ");
		sql.append("WHERE p.payment_status = 'đã thanh toán' AND YEAR(o.order_date) = YEAR(CURDATE());");
		
		return this.gets(sql.toString());
	}
	
	public static void main(String[] args) {
		payment p=new paymentImpl();
		ArrayList<ResultSet> res=p.getRevenueData(null);
		ResultSet rs;
		rs = res.get(0);
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("---Doanh thu ngày: " + rs.getInt("daily_revenue"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = res.get(1);
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("---Doanh thu tháng: " + rs.getInt("monthly_revenue"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = res.get(2);
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("---Doanh thu năm: " + rs.getInt("yearly_revenue"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
