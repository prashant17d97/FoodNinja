package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import core.NavHostController
import core.ResourcePath.Drawable.contentDescription
import core.ResourcePath.Drawable.iconFacebook
import core.ResourcePath.Drawable.iconGoogle
import core.ResourcePath.Drawable.iconLock
import core.ResourcePath.Drawable.iconMail
import core.ResourcePath.Drawable.iconProfile
import core.ResourcePath.Drawable.iconVisibility
import core.ResourcePath.Drawable.iconVisibilityOff
import core.ResourcePath.Drawable.loginLogo
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.CommonElements.NinjaButton

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {

    val focusManager = LocalFocusManager.current
    var isLogin by remember { mutableStateOf(true) }
    var isVisible by remember { mutableStateOf(false) }
    var keepMeSignIn by remember { mutableStateOf(false) }
    var emailMe by remember { mutableStateOf(false) }

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    GradiantWithImageColumn(
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp, alignment = Alignment.CenterVertically
        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(loginLogo),
            contentDescription = loginLogo.contentDescription,
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Login To Your Account".takeIf { isLogin } ?: "Sign Up For Free",
            style = MaterialTheme.typography.headlineSmall)

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            )
        ) {
            AnimatedVisibility(!isLogin) {
                TextField(leadingIcon = {
                    Image(
                        painter = painterResource(iconProfile),
                        contentDescription = iconProfile.contentDescription
                    )
                },
                    placeholder = { Text(text = "Name") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                        ),
                    value = name,
                    onValueChange = {
                        name = it
                    })
            }

            TextField(leadingIcon = {
                Image(
                    painter = painterResource(iconMail),
                    contentDescription = iconMail.contentDescription
                )
            },
                placeholder = { Text(text = "Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                value = email,
                onValueChange = {
                    email = it
                })

            TextField(leadingIcon = {
                Image(
                    painter = painterResource(iconLock),
                    contentDescription = iconLock.contentDescription
                )
            },
                trailingIcon = {
                    Image(painter = painterResource(iconVisibilityOff.takeIf { isVisible }
                        ?: iconVisibility), contentDescription = iconLock.contentDescription,
                        modifier = Modifier.clickable {
                            isVisible = !isVisible
                        })
                },
                placeholder = { Text(text = "Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                value = password,
                onValueChange = {
                    password = it
                })
        }


        AnimatedVisibility(isLogin) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 10.dp, alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Or continue with", style = MaterialTheme.typography.bodyLarge)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(15.dp)
                        ).weight(1f).height(55.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 10.dp, alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Image(
                                painter = painterResource(iconFacebook),
                                contentDescription = iconFacebook.contentDescription
                            )
                            Text(text = "Facebook", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(15.dp)
                        ).weight(1f).height(55.dp), contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 10.dp, alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Image(
                                painter = painterResource(iconGoogle),
                                contentDescription = iconGoogle.contentDescription
                            )
                            Text(text = "Google", style = MaterialTheme.typography.bodyLarge)
                        }

                    }
                }

                Text(textDecoration = TextDecoration.Underline,
                    text = "Forgot Your Password?",
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.clickable {

                    })
            }
        }
        AnimatedVisibility(!isLogin) {
            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(
                    space = 5.dp, alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.clickable {
                        keepMeSignIn = !keepMeSignIn
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(checked = keepMeSignIn, onCheckedChange = { keepMeSignIn = it })
                    Text(text = "Keep Me Signed In", style = MaterialTheme.typography.labelSmall)
                }
                Row(
                    modifier = Modifier.clickable {
                        emailMe = !emailMe
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start

                ) {
                    Checkbox(checked = emailMe, onCheckedChange = { emailMe = it })
                    Text(
                        text = "Email Me About Special Pricing",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        NinjaButton(text = "Login".takeIf { isLogin } ?: "Create Account", onClick = {
            navHostController.navigate(route = Screens.Onboarding)
        })

        if (isLogin) {
            Spacer(modifier = Modifier.height(10.dp))
        }
        Text(textDecoration = TextDecoration.Underline,
            text = "Create an account!".takeIf { isLogin } ?: "Already have an account?",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.clickable {
                isLogin = !isLogin
            })
    }
}