package com.example.ebookapp.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ebookapp.R
import com.example.ebookapp.ui.theme.EBookAppTheme

@Composable
fun AboutScreen(
    modifier: Modifier,
) {
    val image = painterResource(R.drawable.profile)
    val name = stringResource(R.string.MyName)
    val email = stringResource(R.string.MyEmail)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.About),
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(innerPadding)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.h2,
                fontSize = 24.sp
            )
            Spacer(
                modifier = Modifier.padding(innerPadding)
            )
            Text(
                text = email,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    EBookAppTheme {
        AboutScreen(
            modifier = Modifier
        )
    }
}