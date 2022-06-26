package com.jxareas.stonks.presentation.companyListings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun CompanyListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: CompanyListingsViewModel = hiltViewModel(),
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        BasicTextField(value = state.searchQuery,
            onValueChange = { queryString ->
                viewModel.onEvent(CompanyListingsEvent.OnSearchQueryChange(queryString))
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            singleLine = true
        )
        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(CompanyListingsEvent.Refresh)
        }) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.companies.size) { index ->
                    val company = state.companies[index]
                    CompanyItem(company = company, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            // TODO: Navigate to the Details Screen
                        })

                    if (index < state.companies.size)
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                }
            }

        }
    }


}