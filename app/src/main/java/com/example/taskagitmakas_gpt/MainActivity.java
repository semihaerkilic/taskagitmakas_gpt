package com.example.taskagitmakas_gpt;

// Gerekli Android ve Java kütüphanelerini import ediyoruz
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //mainactivity ana ekranı temsil ediyor
    //Oyuncu ve rakibin seçimlerini göstermek için görseller (ImageView) yeri geldiğinde rasgele yeri
    //geldiğinde de bizim oyuncu olarak oynadığımızda seçtiğimiz seçenekleri orada göstereceğiz ve rakip
    //ve oyuncu arasında ki ikili oyunu bize yansıtarak göstericek

    ImageView oyuncu, rakip ;// Oyuncu ve rakibin seçim yapabileceği seçenekler

    String[] secenekler = {"Taş" , "Kağıt", "Makas"};  // Oyuncu ve rakibin yaptığı seçimler ("Taş", "Kağıt" veya "Makas")

    String oyuncusecimi;
    //geliyormuş yani oyun sırasında oyuncunun hangi seçeneği seçerse bu oyuncusecimine aktarılıyor ve oyun boyunca
    //bu değişkenler seçildikçe bu değişkenleri kullanarak oyunu devam ettirioruz.
    String rakipsecimi;

    Random rasgele = new Random();// Rastgele seçim yapmak için Random sınıfı kullanılıyor

    int oyuncupuan, rakippuan;  // Oyuncu ve rakibin puanları

    TextView oyuncupuani, rakippuani; // Oyuncu ve rakibin puanını gösteren metin alanları

    String secim;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Oyuncu ve rakip görselleri ile metin alanlarını bağlıyoruz
        oyuncu = findViewById(R.id.oyuncu);
        rakip = findViewById(R.id.rakip);
        rakippuani = findViewById(R.id.rakippuani);
        oyuncupuani = findViewById(R.id.oyuncupuani);

    }//bu adımda oyun ekranı görünür olur.

    // Oyuncu "Taş" seçimini yaptığında çalışır
    public void tas(View view){
        oyuncu.setImageResource(R.drawable.tas);// Oyuncu görselini "Taş" olarak ayarla

        oyuncusecimi = "Taş";// Oyuncunun seçimi "Kağıt" olarak ayarlanır

        rakipsecimi = secenekler[rasgele.nextInt(3)];// Rakip rastgele bir seçim yapar

        ayarlarakipgorsel(rakipsecimi);// Rakibin görseli güncellenir

        oyna();// Oyunu oyna fonksiyonuna gönderilir

    }

    // Oyuncu "Makas" seçimini yaptığında çalışır
    public void kagit(View view){
        oyuncu.setImageResource(R.drawable.kagit);// Oyuncu görselini "Makas" olarak ayarla

        oyuncusecimi = "Kağıt";// Oyuncunun seçimi "Makas" olarak ayarlanır

        rakipsecimi = secenekler[rasgele.nextInt(3)];// Rakip rastgele bir seçim yapar

        ayarlarakipgorsel(rakipsecimi);// Rakibin görseli güncellenir

        oyna();// Oyunu oyna fonksiyonuna gönderilir
    }

    // Oyuncu ve rakibin otomatik rastgele seçim yaptığı fonksiyon
    public void makas(View view){
        oyuncu.setImageResource(R.drawable.makas);// Oyuncu rastgele bir seçim yapar

        oyuncusecimi = "Makas";// Rakip rastgele bir seçim yapar

        rakipsecimi = secenekler[rasgele.nextInt(3)];// Oyuncunun görseli güncellenir

        ayarlarakipgorsel(rakipsecimi);// Rakibin görseli güncellenir

        oyna();// Oyunu oyna fonksiyonuna gönderilir
    }

    // Oyuncu ve rakibin otomatik rastgele seçim yaptığı fonksiyon
    public void otomatikoyna(View view){
        oyuncusecimi = secenekler[rasgele.nextInt(3)];// Oyuncu rastgele bir seçim yapar
        ayarlaoyuncugörsel(oyuncusecimi);// Oyuncunun görseli güncellenir

        rakipsecimi = secenekler[rasgele.nextInt(3)];// Rakip rastgele bir seçim yapar
        ayarlarakipgorsel(rakipsecimi);// Rakibin görseli güncellenir
        oyna();// Oyunu oyna fonksiyonuna gönderilir
    }

    // Oyuncunun seçimine uygun görseli ayarlayan fonksiyon
    private void ayarlarakipgorsel(String view){
        if (secim.equals ("Taş")){
            rakip.setImageResource(R.drawable.tas);// "Taş" görseli ayarlanır
        } else if (secim.equals("Kağıt")) {
            rakip.setImageResource(R.drawable.kagit);// "Kağıt" görseli ayarlanır
        }else if (secim.equals("Makas")){
            rakip.setImageResource(R.drawable.makas);// "Kağıt" görseli ayarlanır
        }
    }

    // Rakibin seçimine uygun görseli ayarlayan fonksiyon
    private void ayarlaoyuncugörsel(String view){

        if (secim.equals ("Taş")){
            rakip.setImageResource(R.drawable.tas);// "Taş" görseli ayarlanır
        } else if (secim.equals("Kağıt")) {
            rakip.setImageResource(R.drawable.kagit);// "Kağıt" görseli ayarlanır
        }else if (secim.equals("Makas")){
            rakip.setImageResource(R.drawable.makas);// "Makas" görseli ayarlanır
        }
    }

        // Oyunun sonucunu hesaplayan fonksiyon
        private void oyna() {
            // Bu satırda oyuncunun ve rakibin aynı seçimi yapıp yapmadığını kontrol ediyorsunuz.
            //Eğer aynıysa sonuç 'berabere' olarak işaretleniyor.
            if (oyuncusecimi.equals(rakipsecimi)) {
                Toast.makeText(this, "Berabere", Toast.LENGTH_SHORT).show();

                //bu satır ise oyuncunun rakibi yendiği durumları inceliyor.
            } else if ((oyuncusecimi.equals("Taş") && rakipsecimi.equals("Makas")) ||
                    (oyuncusecimi.equals("Kağıt") && rakipsecimi.equals("Taş")) ||
                    (oyuncusecimi.equals("Makas") && rakipsecimi.equals("Kağıt"))) {
                oyuncupuan += 10; // Oyuncunun puanı kazandığında 10 artırılıyor
                Toast.makeText(this, "Oyuncu Kazandı", Toast.LENGTH_SHORT).show();
            } else {
                rakippuan +=10; // Rakibin puanı kazandığında 10 artırılıyor
                Toast.makeText(this, "Rakip Kazandı", Toast.LENGTH_SHORT).show();
            }
            oyuncupuani.setText("Puan: " + oyuncupuan);
            rakippuani.setText("Puan: " + rakippuan);
        }

    }
