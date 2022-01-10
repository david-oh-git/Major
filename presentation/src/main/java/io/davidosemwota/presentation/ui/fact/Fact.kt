package io.davidosemwota.presentation.ui.fact

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.davidosemwota.domain.FactItem
import io.davidosemwota.presentation.R
import io.davidosemwota.presentation.ui.theme.MajorTheme

@Composable
fun FactScreen(viewModel: FactViewModel = viewModel()) {
    val state by viewModel.viewState.collectAsState()

    when {
        state.isLoading -> {
            LoadingScreen()
        }
        state.onError -> {
            ErrorScreen(fetchFact = viewModel::fetchFact)
        }
        state.factItem != null -> {
            FactCard(factItem = state.factItem!!, fetchFact = { viewModel.fetchFact() } )
        }
        else -> {
            ErrorScreen(fetchFact = viewModel::fetchFact)
        }
    }

}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.padding(all = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(
    fetchFact: () -> Unit
) {
    TextButton(
        onClick = fetchFact
    ) {
        Text(text = "Refresh")
    }
}

@Composable
fun FactCardContent(
    factItem: FactItem,
    fetchFact: () -> Unit
) {
    Column(
        modifier = Modifier.padding(all = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.fact_card_title),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
        )

        Divider(modifier = Modifier.padding(4.dp))
        Text(
            modifier = Modifier.padding(all = 4.dp),
            text = factItem.fact
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = fetchFact
        ) {
            Text(text = "Fetch fact", modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun FactCard(
    factItem: FactItem,
    fetchFact: () -> Unit
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        FactCardContent(factItem = factItem, fetchFact)
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun FactPreview() {
    MajorTheme {
        FactScreen()
    }
}