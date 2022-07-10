package github.abbasbanisaeed.digitoon_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.abbasbanisaeed.digitoon_test.R
import github.abbasbanisaeed.digitoon_test.databinding.ActivityMainBinding
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)

        binding.apply {
            navController=findNavController(R.id.navHost)
        }

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onNavigateUp()
    }


}