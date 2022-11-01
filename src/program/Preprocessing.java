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
	JScrollPane logScrollPane;
    
    public Preprocessing() {
        setTitle("Preprocessing Program");
        getContentPane().setLayout(new FlowLayout());
		
		String[] DB_LIST = DBUse();
		lb1 = new JLabel("DB 선택 : ");
		cb1 = new JComboBox(DB_LIST);
		cb1.setModel(new DefaultComboBoxModel(new String[] {"acaChl1", "ailMel1", "allMis1", "allSin1", "amaVit1", "anaPla1", "ancCey1", "angJap1", "anoCar1", "anoCar2", "anoGam1", "anoGam3", "apaSpi1", "apaVit1", "apiMel1", "apiMel2", "aplCal1", "aptFor1", "aptMan1", "aquChr2", "araMac1", "ascSuu1", "balAcu1", "balPav1", "bisBis1", "bosTau2", "bosTau3", "bosTau4", "bosTau5", "bosTau6", "bosTau7", "bosTau8", "bosTau9", "bosTauMd3", "braFlo1", "bruMal2", "bucRhi1", "burXyl1", "caeAng2", "caeJap1", "caeJap4", "caePb1", "caePb2", "caePb3", "caeRem2", "caeRem3", "caeRem4", "caeSp111", "caeSp51", "calAnn1", "calJac1", "calJac3", "calJac4", "calMil1", "canFam1", "canFam2", "canFam3", "canFam4", "canFam5", "canFam6", "capCar1", "carCri1", "cavPor3", "cb1", "cb3", "cb4", "ce10", "ce11", "ce2", "ce4", "ce6", "cerSim1", "chaVoc2", "cheMyd1", "chlSab2", "chlUnd1", "choHof1", "chrPic1", "chrPic2", "ci1", "ci2", "ci3", "colLiv1", "colStr1", "corBra1", "corCor1", "cotJap2", "criGri1", "criGriChoV1", "criGriChoV2", "cucCan1", "danRer1", "danRer10", "danRer11", "danRer2", "danRer3", "danRer4", "danRer5", "danRer6", "danRer7", "dasNov3", "dipOrd1", "dirImm1", "dm1", "dm2", "dm3", "dm6", "dp2", "dp3", "droAna1", "droAna2", "droEre1", "droGri1", "droMoj1", "droMoj2", "droPer1", "droSec1", "droSim1", "droSim2", "droVir1", "droVir2", "droYak1", "droYak2", "eboVir3", "echTel1", "echTel2", "egrGar1", "enhLutNer1", "equCab1", "equCab2", "equCab3", "eriEur1", "eriEur2", "eurHel1", "falChe1", "falPer1", "felCat3", "felCat4", "felCat5", "felCat8", "felCat9", "ficAlb2", "fr1", "fr2", "fr3", "fulGla1", "gadMor1", "galGal2", "galGal3", "galGal4", "galGal5", "galGal6", "galVar1", "gasAcu1", "gavSte1", "gbMeta", "geoFor1", "go", "go080130", "go140213", "go150121", "go180426", "gorGor3", "gorGor4", "gorGor5", "gorGor6", "haeCon2", "halAlb1", "halLeu1", "hetBac1", "hetGla1", "hetGla2", "hg16", "hg17", "hg18", "hg19", "hg19Patch10", "hg19Patch13", "hg38", "hg38Patch11", "hgFixed", "hgcentral", "hs1", "information_schema", "latCha1", "lepDis1", "letCam1", "loaLoa1", "loxAfr3", "macEug1", "macEug2", "macFas5", "manPen1", "melGal1", "melGal5", "melHap1", "melInc2", "melUnd1", "merNub1", "mesUni1", "micMur1", "micMur2", "mm10", "mm39", "mm5", "mm6", "mm7", "mm8", "mm9", "monDom1", "monDom4", "monDom5", "musFur1", "myoLuc2", "nanPar1", "nasLar1", "necAme1", "neoSch1", "nipNip1", "nomLeu1", "nomLeu2", "nomLeu3", "ochPri2", "ochPri3", "oncVol1", "opiHoa1", "oreNil1", "oreNil2", "oreNil3", "ornAna1", "ornAna2", "oryCun2", "oryLat2", "otoGar3", "oviAri1", "oviAri3", "oviAri4", "panPan1", "panPan2", "panPan3", "panRed1", "panTro1", "panTro2", "panTro3", "panTro4", "panTro5", "panTro6", "papAnu2", "papAnu4", "papHam1", "pelCri1", "pelSin1", "performance_schema", "petMar1", "petMar2", "petMar3", "phaCar1", "phaLep1", "phoRub1", "picPub1", "ponAbe2", "ponAbe3", "priExs1", "priPac1", "priPac3", "proCap1", "proteins120806", "proteins121210", "proteins140122", "proteins150225", "proteins160229", "proteins180404", "proteome", "pteGut1", "pteVam1", "pygAde1", "pytBiv1", "rheMac1", "rheMac10", "rheMac2", "rheMac3", "rheMac8", "rhiRox1", "rn3", "rn4", "rn5", "rn6", "rn7", "sacCer1", "sacCer2", "sacCer3", "saiBol1", "sarHar1", "serCan1", "sorAra1", "sorAra2", "sp120323", "sp121210", "sp140122", "sp150225", "sp160229", "sp180404", "speTri2", "strCam1", "strPur1", "strPur2", "strRat2", "susScr11", "susScr2", "susScr3", "sys", "taeGut1", "taeGut2", "tarSyr1", "tarSyr2", "tauEry1", "tetNig1", "tetNig2", "thaSir1", "tinGut2", "triMan1", "triSpi1", "triSui1", "tupBel1", "turTru2", "tytAlb1", "uniProt", "vicPac1", "vicPac2", "visiGene", "wuhCor1", "xenLae2", "xenTro1", "xenTro10", "xenTro2", "xenTro3", "xenTro7", "xenTro9", "zonAlb1"}));
		String DB_NAME = cb1.getSelectedItem().toString();
		
		String[] TABLE_LIST = TableShow(DB_NAME);
		lb2 = new JLabel("Table 선택 : ");
		cb2 = new JComboBox(TABLE_LIST);
		String TABLE_NAME = cb2.getSelectedItem().toString();

		//이제 DB 바꾸면 Table 바뀌도록 설정해야함
		
		String[] TABLE_ROW = TableSelect(DB_NAME, TABLE_NAME);
		
		ta = new JTextArea(40, 80);
		logScrollPane = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		for (String row : TABLE_ROW) {
			ta.append(row + "\n");
			ta.setCaretPosition(ta.getDocument().getLength());
		}
		//add(buttonPanel, BorderLayout.PAGE_START);
		getContentPane().add(lb1);
		getContentPane().add(cb1);
		getContentPane().add(lb2);
		getContentPane().add(cb2);
		getContentPane().add(logScrollPane);
		
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
    
    
    
    public static String[] TableShow(String DB_NAME) {

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
    
    
    public static String[] TableSelect(String DB_NAME, String TABLE_NAME) {

		Connection conn = DBconnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sbf = new StringBuffer();
		StringBuffer namedb = new StringBuffer();
		StringBuffer nametable = new StringBuffer();
		
		try {
			namedb.append("use ");
			namedb.append(DB_NAME);
			String sql = namedb.toString();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			nametable.append("select * from ");
			nametable.append(TABLE_NAME);
			sql = nametable.toString();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false를 반환한다.
				// 이 부분도 나중에 바꿔야함, 바꾸는 김에 나중에 JTable으로 하기
				sbf.append(rs.getString(1) + "\t\t" + rs.getInt(2) + "\t\t" + rs.getString(3));
				sbf.append("\n");
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}


		String TABLE = sbf.toString();
		System.out.println(TABLE);
		String[] TABLE_ROW = TABLE.split("\n");
		System.out.println(Arrays.toString(TABLE_ROW));
		
		return TABLE_ROW;
    }
    
    
    
    public static void main(String args[]) {
    	
    	Preprocessing p = new Preprocessing();
    }
	
}
