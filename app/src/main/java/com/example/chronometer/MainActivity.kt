package com.example.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import com.example.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding //onCreate methodundan önce ActivityMainBinding tipinde binding isminde bir degisken olusturduk.
    //data binding, view objelerine binding nesnesi üzerinden erisimi sagliyor.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var timeStop:Long=0 //ilk basta herhangi bir zaman yok...
        // bu sebeple ilk degeri sifir olarak belirliyoruz.
        binding.btnStart.setOnClickListener{
             //start butonuna bastigimizda gerceklesecek islemleri burada yaziyoruz.
             binding.chronometer.base=SystemClock.elapsedRealtime()+timeStop
            //yukarda ki satırla kronometrenin icerisine sistem saatini aktariyoruz
            //kronometreyi çalistirip durdurduktan sonra tekrar kaldigi yerden devam edebilmesi icin...
            //timeStop degiskenini ekliyoruz
             binding.chronometer.start()//kronometreyi calistir
             binding.btnStart.visibility=View.GONE
             //Kronometreyi baslattigimizda btnStart'in kaybolmasi ve
             // btnPause'nin görünür hale gelmesi icin btnStart'i pasif, btnPause'yi aktif yapiyoruz.
             binding.btnPause.visibility=View.VISIBLE
             binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))
        }
        binding.btnPause.setOnClickListener{
            timeStop=binding.chronometer.base-SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnPause.visibility=View.GONE
            binding.btnStart.visibility=View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }
        binding.btnReset.setOnClickListener{
            binding.chronometer.base=SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnPause.visibility=View.GONE
            binding.btnStart.visibility=View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }
    }
}