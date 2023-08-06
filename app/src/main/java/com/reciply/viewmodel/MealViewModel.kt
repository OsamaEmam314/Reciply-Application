
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reciply.data.models.Meal
import com.reciply.data.network.ApiClient
import com.reciply.repo.MealsRepository
import kotlinx.coroutines.launch

class MealViewModel(val mealsRepository: MealsRepository):ViewModel() {
    private val _MealListBYName = MutableLiveData<List<Meal>>()
    val MealListBYNmae: LiveData<List<Meal>> = _MealListBYName

    private val _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal

    private val _listOfMealsByLetter = MutableLiveData<List<Meal>>()
    val listOfMealsByLetter: LiveData<List<Meal>> = _listOfMealsByLetter

    fun getMealByName(Name: String) {
        viewModelScope.launch {
            val response = ApiClient.getMealByName(Name)
            Log.d("asd->", "listofMeals:$response ")
            _MealListBYName.value = response.meals

        }
    }


    fun getRandomMeal() {
        viewModelScope.launch {
            val rand = mealsRepository.getRemoteRandomMeal()
            Log.d("random->", "Random Meal:${rand.meals[0]} ")
            _randomMeal.value=rand.meals[0]
        }
    }

    fun listMealsByLetter() {
        viewModelScope.launch {
            val randLetter = ('A'..'Z').random().toString()
            val response = mealsRepository.getRemoteMealsList(randLetter)
            _listOfMealsByLetter.value = response.meals
        }
    }
}
