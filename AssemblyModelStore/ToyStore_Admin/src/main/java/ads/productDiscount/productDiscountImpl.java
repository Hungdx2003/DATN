package ads.productDiscount;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.basic.basicImpl;
import ads.objects.ProductDiscount;

public class productDiscountImpl extends basicImpl implements productDiscount {

	public productDiscountImpl() {
		super("Product Discount");
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean addProductDiscount(ArrayList<ProductDiscount> items) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO product_discounts(");
        sql.append("product_id, discount_id) ");
        sql.append("VALUES(?,?)");

        try {
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            for (ProductDiscount item : items) {
                pre.setInt(1, item.getProduct_id());
                pre.setInt(2, item.getDiscount_id());
                pre.addBatch();
            }
            return this.addBatch(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editProductDiscount(ArrayList<ProductDiscount> items) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE product_discounts SET ");
        sql.append("product_id=?, discount_id=? ");
        sql.append("WHERE pd_id=?");

        try {
        	PreparedStatement pre = this.con.prepareStatement(sql.toString());
            for (ProductDiscount item : items) {
                pre.setInt(1, item.getProduct_id());
                pre.setInt(2, item.getDiscount_id());
                pre.setInt(3, item.getPd_id());
                pre.addBatch();
            }
            return this.editBatch(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delProductDiscount(ArrayList<ProductDiscount> items) {
        String sql = "DELETE FROM product_discounts WHERE pd_id=?";

        try {
        	 PreparedStatement pre = this.con.prepareStatement(sql);
            for (ProductDiscount item : items) {
                pre.setInt(1, item.getPd_id());
                pre.addBatch();
            }
            return this.delBatch(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean delProductDiscount(ProductDiscount item) {
        String sql = "DELETE FROM product_discounts WHERE pd_id=?";

        try {
        	 PreparedStatement pre = this.con.prepareStatement(sql);
             pre.setInt(1, item.getPd_id());
            return this.del(pre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        // Giả sử bạn có một productDiscountModel có constructor không tham số
        productDiscount pdm = new productDiscountImpl() ;

//        // Tạo danh sách ProductDiscount cần xóa (giả sử pd_id là các số thực tế có trong CSDL)
//        ArrayList<ProductDiscount> listToDelete = new ArrayList<>();
//
//        ProductDiscount pd1 = new ProductDiscount();
//        pd1.setPd_id(6);  // Ví dụ id 1
//        listToDelete.add(pd1);
//
//        ProductDiscount pd2 = new ProductDiscount();
//        pd2.setPd_id(22);  // Ví dụ id 2
//        listToDelete.add(pd2);
//
//        // Gọi hàm xóa
//        boolean result = pdm.delProductDiscount(listToDelete);
        ProductDiscount pd1 = new ProductDiscount();
        pd1.setPd_id(23); 
        boolean result = pdm.delProductDiscount(pd1);

        if (result) {
            System.out.println("Xóa thành công các sản phẩm khuyến mãi.");
        } else {
            System.out.println("Xóa không thành công.");
        }
    }
}
