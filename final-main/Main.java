/*
22120205020 Bahar Kayhan
22120205027 Beyza Nur Çağlar
22120205028 Azra Dinler

*/

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Form nesnesini oluşturarak görünür hale getiriliyor
        java.awt.EventQueue.invokeLater(() -> new Form().setVisible(true));
    }
}


class Form extends javax.swing.JFrame {

    public javax.swing.JButton btnPhoto;
    public javax.swing.JButton btnShare;
    public javax.swing.JButton btnBlackWhite;
    public javax.swing.JButton btnGreen;
    public javax.swing.JButton btnBrightening;
    public javax.swing.JButton btnSave;
    public javax.swing.JButton btnSave1;
    public static javax.swing.JButton lblPhoto;
    public javax.swing.JLabel lblPhoto1;
    public Mat frame;


    public Form() {
        initComponents();
        setLocationRelativeTo(null); //ekranın ortasına konumlandırma
    }


    private void initComponents() {

        lblPhoto = new JButton();
        btnPhoto = new JButton();
        btnShare = new JButton();
        btnBlackWhite = new JButton();
        btnBrightening = new JButton();
        btnGreen = new JButton();
        lblPhoto1 = new javax.swing.JLabel();
        btnSave = new JButton();
        btnSave1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPhoto.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnPhoto.setForeground(new java.awt.Color(0, 102, 51));
        btnPhoto.setText("Fotoğraf Çek");
        btnPhoto.addActionListener(this::btnPhotoActionPerformed);

        btnShare.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnShare.setForeground(new java.awt.Color(0, 102, 51));
        btnShare.setText("Paylaş");
        btnShare.addActionListener(this::btnShareActionPerformed);

        btnBlackWhite.setBackground(new java.awt.Color(51, 51, 51));
        btnBlackWhite.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnBlackWhite.setForeground(new java.awt.Color(255, 255, 255));
        btnBlackWhite.setText("Siyah-Beyaz");
        btnBlackWhite.addActionListener(this::btnBlackWhiteActionPerformed);

        btnBrightening.setBackground(new java.awt.Color(204, 204, 255));
        btnBrightening.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnBrightening.setForeground(new java.awt.Color(102, 0, 255));
        btnBrightening.setText("Aydınlık");
        btnBrightening.addActionListener(this::btnBrighteningActionPerformed);

        btnGreen.setBackground(new java.awt.Color(51, 102, 0));
        btnGreen.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnGreen.setForeground(new java.awt.Color(51, 255, 0));
        btnGreen.setText("Yeşil Efekt");
        btnGreen.addActionListener(this::btnGreenActionPerformed);

        btnSave.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 102, 0));
        btnSave.setText("Kaydet");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnSave1.setFont(new java.awt.Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(51, 102, 0));
        btnSave1.setText("Kaydet");
        btnSave1.addActionListener(this::btnSave1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(182, 182, 182)
                                                .addComponent(btnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnBrightening, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnBlackWhite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(116, 116, 116))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPhoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnShare, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPhoto1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(btnBlackWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBrightening, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(btnPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSave)))
                                .addGap(18, 18, 18)
                                .addComponent(btnSave1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(btnShare, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))
        );

        pack();
    }

    private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt) {
        Mat frame = Fotograf.captureAndShow();
        this.frame = frame;
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        Mat frame = this.frame;
        Islemler.dosyaIslemleri(frame);
    }

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {
        // lblPhoto1'daki ikonu al
        ImageIcon icon = (ImageIcon) lblPhoto1.getIcon();
        Image image = icon.getImage();

        // BufferedImage oluştur
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        Islemler.dosyaIslemleri2(bufferedImage);

    }


    // lblPhoto'da gösterilen resmi siyah-beyaz yap
    private void btnBlackWhiteActionPerformed(java.awt.event.ActionEvent evt) {
        // Orijinal resmin ikonunu al
        ImageIcon originalIcon = (ImageIcon) lblPhoto.getIcon();
        Image originalImage = originalIcon.getImage();

        // Orijinal resim boyutunda bir BufferedImage oluştur
        BufferedImage bufferedImage = new BufferedImage(originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, null);
        g2.dispose();

        // Siyah-beyaz efektini uygula
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (r + g + b) / 3;
                int grayRGB = (gray << 16) | (gray << 8) | gray;
                bufferedImage.setRGB(x, y, grayRGB);
            }
        }

        ImageIcon blackWhiteIcon = new ImageIcon(bufferedImage);      // Siyah-beyaz ikonunu oluştur
        lblPhoto1.setIcon(blackWhiteIcon);          // lblPhoto1 bileşenine siyah-beyaz ikonunu ata


    }

    private void btnBrighteningActionPerformed(java.awt.event.ActionEvent evt) {
        // lblPhoto'da gösterilen resmi parlaklık ayarını kullanarak güncelle
        ImageIcon originalIcon = (ImageIcon) lblPhoto.getIcon();
        Image originalImage = originalIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, null);
        g2.dispose();

        float brightnessScale = 2.1f; // Parlaklık ölçeği (örnek olarak 1.2 olarak ayarlanmış)
        RescaleOp rescaleOp = new RescaleOp(brightnessScale, 0, null); // Parlaklık ayarı için RescaleOp nesnesi oluştur

        BufferedImage brightenedImage = rescaleOp.filter(bufferedImage, null);
        ImageIcon brightenedIcon = new ImageIcon(brightenedImage);          // Aydınlık ikonunu oluştur
        lblPhoto1.setIcon(brightenedIcon);           // lblPhoto1 bileşenine aydınlık ikonunu ata
    }

    private void btnGreenActionPerformed(java.awt.event.ActionEvent evt) {
        // lblPhoto'da gösterilen resmi yeşil efekt vererek lblPhoto1'de göster

        // Orijinal resmi al
        ImageIcon originalIcon = (ImageIcon) lblPhoto.getIcon();
        Image originalImage = originalIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, null);
        g2.dispose();

        // Yeşil efektini uygula
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Yeşil kanalını artır
                g = Math.min(g + 50, 255);

                int greenRGB = (r << 16) | (g << 8) | b;
                bufferedImage.setRGB(x, y, greenRGB);
            }
        }

        // Yeşil efektli resmi lblPhoto1'de göster
        ImageIcon greenIcon = new ImageIcon(bufferedImage);     // Yeşil efekt ikonunu oluştur
        lblPhoto1.setIcon(greenIcon);       // lblPhoto1 bileşenine yeşil efekt ikonunu ata

    }

    private void btnShareActionPerformed(java.awt.event.ActionEvent evt) {

    }

}

class Fotograf extends javax.swing.JFrame {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);      //openCV kütüphanesinin yüklenmesini sağlar
    }

    //Fotoğraf çekme işlemleri
    public static Mat captureAndShow() {
        VideoCapture videoCapture = new VideoCapture(0);  // Kamera cihazına erişim sağlar

        if (!videoCapture.isOpened()) {
            System.out.println("Kamera bulunamadı veya açılamadı.");
        }

        Mat frame = new Mat();
        videoCapture.read(frame);  // Kameradan bir kare al

        if (frame.empty()) {
            System.out.println("Kare alınamadı.");
        } else {

            // Fotoğrafın boyutunu lblPhoto'ya sığacak şekilde ölçeklendir
            int lblWidth = Form.lblPhoto.getWidth();
            int lblHeight = Form.lblPhoto.getHeight();
            Mat scaledFrame = new Mat();
            Imgproc.resize(frame, scaledFrame, new Size(lblWidth, lblHeight));

            // Ölçeklendirilmiş kareyi lblPhoto'da göster
            BufferedImage image = matToBufferedImage(scaledFrame);
            ImageIcon icon = new ImageIcon(image);
            Form.lblPhoto.setIcon(icon);
        }

        return frame;
    }

    // Mat nesnesini BufferedImage'ye dönüştüren yardımcı metod
    public static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer); // Mat verilerini byte dizisine kopyala
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, bufferSize); // Byte dizisini BufferedImage'e kopyala
        return image;
    }
}

class Islemler extends Component {

    public static void dosyaIslemleri(Mat frame) {
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        String folderName = JOptionPane.showInputDialog(null, "Fotoğrafı hangi klasöre kaydetmek istersiniz?");  // Kullanıcıdan kaydedilecek klasör adı alınıyor
        if (folderName != null && !folderName.trim().isEmpty()) {
            String folderPath = desktopPath + File.separator + folderName;  // Klasörün tam yolu oluşturuluyor

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();  // Klasör yoksa oluşturuluyor
                JOptionPane.showMessageDialog(null, "Yeni klasör oluşturuldu: " + folderPath);  // Kullanıcıya yeni klasör oluşturulduğu bilgisi veriliyor
            }

            String fileName = JOptionPane.showInputDialog(null, "Fotoğrafın dosya adını girin:");  // Kullanıcıdan dosya adı alınıyor
            if (fileName != null && !fileName.trim().isEmpty()) {
                String filePath = folderPath + File.separator + fileName + ".png";  // Dosyanın tam yolu oluşturuluyor
                Imgcodecs.imwrite(filePath, frame);  // Kareyi dosyaya kaydediliyor
                JOptionPane.showMessageDialog(null, "Fotoğraf kaydedildi.");  // Kullanıcıya fotoğrafın başarıyla kaydedildiği bilgisi veriliyor
            }

            Form.lblPhoto.setHorizontalAlignment(SwingConstants.CENTER); // Resmi ortalamak için yatay hizalama ayarı
            Form.lblPhoto.setVerticalAlignment(SwingConstants.CENTER); // Resmi ortalamak için dikey hizalama ayarı

            // Resmi jLabel'da göster
            String filePath = folderPath + File.separator + fileName + ".png";  // Dosyanın tam yolu oluşturuluyor
            File file = new File(filePath);

            try {
                BufferedImage image = ImageIO.read(file);   //resim dosyası okunuyor
                if (image != null) {
                    Image scaledImage = image.getScaledInstance(Form.lblPhoto.getWidth(), Form.lblPhoto.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(scaledImage);
                    Form.lblPhoto.setIcon(imageIcon);
                } else {
                    System.out.println("Resim yüklenemedi: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Resim yüklenirken bir hata oluştu: " + e.getMessage());
            }
        }
    }


    public static void dosyaIslemleri2(BufferedImage image) {
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        String folderName = JOptionPane.showInputDialog(null, "Fotoğrafı hangi klasöre kaydetmek istersiniz?");
        if (folderName != null && !folderName.trim().isEmpty()) {
            String folderPath = desktopPath + File.separator + folderName;

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
                JOptionPane.showMessageDialog(null, "Yeni klasör oluşturuldu: " + folderPath);
            }

            String fileName = JOptionPane.showInputDialog(null, "Fotoğrafın dosya adını girin:");
            if (fileName != null && !fileName.trim().isEmpty()) {
                String filePath = folderPath + File.separator + fileName + ".png";
                File file = new File(filePath);

                try {
                    ImageIO.write(image, "png", file);
                    JOptionPane.showMessageDialog(null, "Fotoğraf kaydedildi.");
                } catch (IOException e) {
                    System.out.println("Resim kaydedilirken bir hata oluştu: " + e.getMessage());
                }
            }
  }

}}