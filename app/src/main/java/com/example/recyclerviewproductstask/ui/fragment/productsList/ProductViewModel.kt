    package com.example.recyclerviewproductstask.ui.fragment.productsList

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.recyclerviewproductstask.repository.ProductRepository
    import com.example.recyclerviewproductstask.ViewMessage
    import com.example.recyclerviewproductstask.api.model.Product
    import com.google.gson.Gson
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch
    import retrofit2.HttpException
    import java.io.IOException
    import javax.inject.Inject

    @HiltViewModel
    class ProductViewModel @Inject constructor(
        private val repository: ProductRepository
    ) : ViewModel() {

        val newsLiveData = MutableLiveData<List<Product?>?>()
        val message = MutableLiveData<ViewMessage>()

        fun loadProducts() {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repository.getProducts()
                    newsLiveData.postValue(response.products)
                } catch (ex: HttpException) {
                    val errorResponse = parseErrorBody(ex)
                    val errorMessage = errorResponse?.message ?: "Something went wrong"
                    message.postValue(ViewMessage(message = errorMessage))
                } catch (ex: IOException) {
                    message.postValue(ViewMessage(message = ex.message ?: "Network error"))
                } catch (ex: Exception) {
                    message.postValue(ViewMessage(message = "Unknown error occurred"))
                }
            }
        }

        private fun parseErrorBody(ex: HttpException): ProductErrorResponse? {
            return try {
                val responseJson = ex.response()?.errorBody()?.string()
                Gson().fromJson(responseJson, ProductErrorResponse::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    data class ProductErrorResponse(
        val message: String? = null,
        val code: String? = null,
        val status: String? = null
    )