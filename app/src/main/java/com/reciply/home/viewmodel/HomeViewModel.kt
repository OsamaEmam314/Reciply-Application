
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.data.models.MealResponse
import com.reciply.home.repo.HomeMealsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val mealsRepository: HomeMealsRepository):ViewModel() {

    private val _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal

    private val _listOfMealsByLetter = MutableLiveData<List<Meal>>()
    val listOfMealsByLetter: LiveData<List<Meal>> = _listOfMealsByLetter


    fun getRandomMeal() {
        viewModelScope.launch {
            val rand = mealsRepository.getRemoteRandomMeal()
            Log.d("random->", "Random Meal:${rand.meals[0]} ")
            _randomMeal.value=rand.meals[0]
        }
    }

    fun listMealsByLetter() {
        viewModelScope.launch {
            var randLetter:String
            var response:MealResponse
            do {
                randLetter = ('A'..'Z').random().toString()
                response = mealsRepository.getRemoteMealsList(randLetter)
            }while(response.meals==null)
            _listOfMealsByLetter.value = response.meals
        }
    }

}
