package program;

import java.awt.*;                 // 그래픽 처리를 위한 클래스
import java.awt.event.*;          // AWT 이벤트 사용을 위한 클래스
import javax.swing.*;              // Swing 컴포넌트 클래스
import javax.swing.event.*;      // Swing 이벤트 사용을 위한 클래스
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Preprocessing extends JFrame {
	JTextField tf1, tf2;
    JTextArea ta;
    JLabel lb1, lb2;
    JComboBox cb1, cb2;
    JButton openButton, saveButton;
	JScrollPane logScrollPane = new JScrollPane(ta);
    
    public Preprocessing() {
        setTitle("Preprocessing Program");
        setLayout(new FlowLayout());
        
        //tf1 = new JTextField(80);
        //tf2 = new JTextField(80);
        //ta = new JTextArea(20,80);
        
        //openButton = new JButton("Open the Databases ...");
        //openButton.addActionListener(new ActionListener(){
		//	public void actionPerformed(ActionEvent e) {
		//		String[] DB_LIST = DBconnect();
		//	}
		//});
        //JPanel buttonPanel = new JPanel(); //use FlowLayout
		//buttonPanel.add(openButton);
		
		String[] DB_LIST = DBUse();
		lb1 = new JLabel("DB 선택 : ");
		cb1 = new JComboBox(DB_LIST);
		String DB_NAME = cb1.getSelectedItem().toString();
		
		String[] TABLE_LIST = TableSelect(DB_NAME);
		lb2 = new JLabel("Table 선택 : ");
		cb2 = new JComboBox(TABLE_LIST);
		String TABLE_NAME = cb2.getSelectedItem().toString();

		//이제 DB 바꾸면 Table 바뀌도록 설정해야함
		
		//add(buttonPanel, BorderLayout.PAGE_START);
		add(lb1);
		add(cb1);
		add(lb2);
		add(cb2);
		
        setSize(1000,750);
        setVisible(true);
        
    }
    
    
    
    public static Connection DBconnect() {

    	final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "genome-mysql.soe.ucsc.edu";
		final String DB_PORT = "3306";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT;

		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, "genome", "");
			if (conn != null) {
				System.out.println("DB 접속 성공");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
		
		return conn;
    }
    
    
    
    public static String[] DBUse() {
    	Connection conn = DBconnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String DB_NAME = "";
		StringBuffer sbf = new StringBuffer();
		
		try {
			String sql = "show databases";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
				sbf.append(rs.getString(1)); //getInt(1)은 컬럼의 1번째 값을 String 형으로 가져온다.
				sbf.append(",");
			}

			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		DB_NAME = sbf.toString();
		System.out.println(DB_NAME);
		String[] DB_LIST = DB_NAME.split(",");
		System.out.println(Arrays.toString(DB_LIST));
		
		return DB_LIST;
    }
    
    
    
    public static String[] TableSelect(String DB_NAME) {

		Connection conn = DBconnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String TABLE_NAME = "";
		StringBuffer sbf = new StringBuffer();
		StringBuffer name = new StringBuffer();
		
		try {
			name.append("use ");
			name.append(DB_NAME);
			String sql = name.toString();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			sql = "show tables";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
				sbf.append(rs.getString(1)); //getInt(1)은 컬럼의 1번째 값을 String 형으로 가져온다.
				sbf.append(",");
			}

			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		TABLE_NAME = sbf.toString();
		System.out.println(TABLE_NAME);
		String[] TABLE_LIST = TABLE_NAME.split(",");
		System.out.println(Arrays.toString(TABLE_LIST));
		
		return TABLE_LIST;
    }
    
    
    
    public static void main(String args[]) {
    	
    	Preprocessing p = new Preprocessing();
    }
	
}
