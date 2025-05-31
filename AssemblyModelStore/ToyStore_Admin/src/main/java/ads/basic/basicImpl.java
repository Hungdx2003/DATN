package ads.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;

public class basicImpl implements basic {
	private String objectName;
	private ConnectionPool cp = ConnectionPoolImpl.getInstance();
	protected Connection con;

	public basicImpl(String objectName) {
		this.objectName = objectName;
		try {
			this.con = this.cp.getConnection(this.objectName);
			// KHÔNG setAutoCommit ở đây
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean exe(PreparedStatement pre) {
		if (pre != null) {
			try {
				if (this.con.getAutoCommit()) {
					this.con.setAutoCommit(false);
				}

				int result = pre.executeUpdate();
				if (result == 0) {
					this.con.rollback();
					return false;
				}

				this.con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					this.con.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean add(PreparedStatement pre) {
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		return this.exe(pre);
	}

	@Override
	public ArrayList<ResultSet> gets(String multiSelect) {
	    ArrayList<ResultSet> res = new ArrayList<>();
	    PreparedStatement stmt = null;
	    try {
	        if (!this.con.getAutoCommit()) {
	            this.con.setAutoCommit(true);
	        }

	        stmt = this.con.prepareStatement(multiSelect);
	        boolean hasResult = stmt.execute();

	        while (true) {
	            if (hasResult) {
	                ResultSet rs = stmt.getResultSet();
	                if (rs != null) {
	                    res.add(rs);
	                }
	            } else {
	                int updateCount = stmt.getUpdateCount();
	                if (updateCount == -1) {
	                    break;
	                }
	            }
	            hasResult = stmt.getMoreResults(Statement.KEEP_CURRENT_RESULT);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        try {
	            this.con.rollback();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return res;
	}

	@Override
	public ResultSet get(String sql, int value) {
		try {
			if (!this.con.getAutoCommit()) {
				this.con.setAutoCommit(true);
			}
			PreparedStatement pre = this.con.prepareStatement(sql);
			if (value > 0) {
				pre.setInt(1, value);
			}
			return pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		try {
			if (!this.con.getAutoCommit()) {
				this.con.setAutoCommit(true);
			}
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, pass);
			return pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ResultSet> gets(String sql, int value) {
	    ArrayList<ResultSet> res = new ArrayList<>();
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    
	    try {
	        if (!this.con.getAutoCommit()) {
	            this.con.setAutoCommit(true);
	        }
	        
	        pre = this.con.prepareStatement(sql);
	        
	        if (value > 0) {
	            pre.setInt(1, value);
	        }
	        
	        rs = pre.executeQuery();
	        
	        res.add(rs);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return res;
	}
	
	
	public boolean exeBatch(PreparedStatement pre) {
	    int totalAffected = 0;
	    try {
	        if (this.con.getAutoCommit()) {
	            this.con.setAutoCommit(false);
	        }

	        int[] results = pre.executeBatch();

	        for (int count : results) {
	            if (count >= 0) {
	                totalAffected += count;
	            }
	        }
	        
	        if (totalAffected > 0) {
	            this.con.commit();
	            return true;
	        } else {
	            this.con.rollback();
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            this.con.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            this.con.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	
	@Override
	public boolean addBatch(PreparedStatement pre) {
	    return this.exeBatch(pre);
	}
	
	@Override
	public boolean editBatch(PreparedStatement pre) {
	    return this.exeBatch(pre);
	}
	
	@Override
	public boolean delBatch(PreparedStatement pre) {
	    return this.exeBatch(pre);
	}

}
