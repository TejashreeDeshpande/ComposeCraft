package com.example.composecraft.features.instacart.di

import com.example.composecraft.features.instacart.data.repository.CartRepositoryImpl
import com.example.composecraft.features.instacart.data.repository.OrderRepositoryImpl
import com.example.composecraft.features.instacart.data.repository.ProductRepositoryImpl
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import com.example.composecraft.features.instacart.domain.repository.OrderRepository
import com.example.composecraft.features.instacart.domain.repository.ProductRepository
import com.example.composecraft.features.instacart.domain.usecase.*
import com.example.composecraft.features.instacart.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val instacartModule = module {
    single<ProductRepository> { ProductRepositoryImpl() }
    single<CartRepository> { CartRepositoryImpl() }
    single<OrderRepository> { OrderRepositoryImpl() }

    factory { GetHomeDataUseCase(get()) }
    factory { SearchProductsUseCase(get()) }
    factory { GetCategoryProductsUseCase(get()) }
    factory { GetProductDetailUseCase(get()) }
    factory { GetSimilarProductsUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { UpdateCartQuantityUseCase(get()) }
    factory { RemoveFromCartUseCase(get()) }
    factory { ApplyPromoCodeUseCase(get()) }
    factory { PlaceOrderUseCase(get(), get()) }
    factory { GetOrdersUseCase(get()) }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { (productId: String) -> ProductDetailViewModel(productId, get(), get(), get()) }
    viewModel { CartViewModel(get(), get(), get(), get()) }
    viewModel { CheckoutViewModel(get(), get(), get()) }
    viewModel { OrdersViewModel(get()) }
}
