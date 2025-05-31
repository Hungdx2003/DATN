package ads.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.objects.ProductObject;
import ads.viewModel.productViewModel;

public class productModel {
	private product p;

	public productModel() {
		this.p = new productImpl();
	}

	public boolean addProduct(ProductObject item) {
		return this.p.addProduct(item);
	}

	public boolean editProduct(ProductObject item) {
		return this.p.editProduct(item);
	}
	
	public boolean editProduct(ArrayList<ProductObject> items) {
		return this.p.editProduct(items);
	}

	public boolean delProduct(ProductObject item) {
		return this.p.delProduct(item);
	}

	public ProductObject getProduct(int id) {
		ProductObject item = null;

		ResultSet rs = this.p.getProduct(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_original_price(rs.getInt("product_original_price"));
					item.setProduct_quantity(rs.getInt("product_quantity"));
					item.setProduct_created_by(rs.getInt("product_created_by"));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_by(rs.getInt("product_modified_by"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_pc_id(rs.getInt("product_pc_id"));
					item.setProduct_detail(rs.getString("product_detail"));
					item.setProduct_sold(rs.getInt("product_sold"));
					item.setProduct_status(rs.getString("product_status"));
					item.setProduct_brand(rs.getString("product_brand"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public ProductObject getProductByCreator(int id) {
		ProductObject item = null;

		ResultSet rs = this.p.getProductByCreator(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_original_price(rs.getInt("product_original_price"));
					item.setProduct_quantity(rs.getInt("product_quantity"));
					item.setProduct_created_by(rs.getInt("product_created_by"));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_by(rs.getInt("product_modified_by"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_pc_id(rs.getInt("product_pc_id"));
					item.setProduct_detail(rs.getString("product_detail"));
					item.setProduct_sold(rs.getInt("product_sold"));
					item.setProduct_status(rs.getString("product_status"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<ProductObject> getProduct(ProductObject similar, int at, byte total) {
	    ArrayList<ProductObject> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getProduct(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	ProductObject item = new ProductObject();
	        	item.setProduct_id(rs.getInt("product_id"));
				item.setProduct_name(rs.getString("product_name"));
				item.setProduct_price(rs.getInt("product_price"));
				item.setProduct_original_price(rs.getInt("product_original_price"));
				item.setProduct_quantity(rs.getInt("product_quantity"));
				item.setProduct_created_by(rs.getInt("product_created_by"));
				item.setProduct_created_date(rs.getString("product_created_date"));
				item.setProduct_modified_by(rs.getInt("product_modified_by"));
				item.setProduct_modified_date(rs.getString("product_modified_date"));
				item.setProduct_pc_id(rs.getInt("product_pc_id"));
				item.setProduct_detail(rs.getString("product_detail"));
				item.setProduct_sold(rs.getInt("product_sold"));
				item.setProduct_status(rs.getString("product_status"));
	            list.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getBestSaleByDay() {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getBestSaleByDay(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductSold(rs.getInt("total_quantity_sold"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getBestSaleByMonth() {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getBestSaleByMonth(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductSold(rs.getInt("total_quantity_sold"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getBestSaleByYear() {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getBestSaleByYear(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductSold(rs.getInt("total_quantity_sold"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getLowQuantity() {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getLowQuantity(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getProductDiscount() {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getProductDiscount(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductStatus(rs.getString("product_status"));
				item.setImageUrl(rs.getString("image_url"));
				
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getProductDiscountById(int id) {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getProductDiscountById(id);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductStatus(rs.getString("product_status"));
				item.setImageUrl(rs.getString("image_url"));
				item.setPd_id(rs.getInt("pd_id"));
				
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getSlowSellingProduct() {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getSlowSellingProduct(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<String> getBrand() {
	    ArrayList<String> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getBrand(null);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	            list.add(rs.getString("product_brand"));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
