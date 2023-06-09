@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.invoice.contratista.ui.custom.component


import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.invoice.contratista.ui.theme.ModifierFieldImages

/**
 * **My Custom TextField**
 *
 * @param hint
 * @param placeholder (optional) indication for the user
 * @param initField (optional) for initial data
 * @param modifier (optional) for custom attributes
 * @param icon (optional) from resource
 * @param visualTransformation (optional) for change to password
 */
@ExperimentalMaterial3Api
@Composable
fun TextField(
    hint: String,
    placeholder: String = "",
    initField: String = "",
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: String = "high",
    isRequired: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    counterEnable: Boolean = false,
    change: (String) -> Unit,
    externalError: MutableState<String> = mutableStateOf("")
) {
    val counterNumber = 0
    var field by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = initField))
    }

    Column(modifier = modifier) {
        // region Field
        if (initField.isNotEmpty()) change.onChange(initField)
        OutlinedTextField(
            value = field,
            onValueChange = {
                field = it
                change.onChange(it.text)
            },
            label = {
                Text(text = "$hint ${if (isRequired) "*" else ""}")
            },
            placeholder = if (placeholder.isEmpty()) null else {
                { Text(text = placeholder) }
            },
            isError = isRequired && field.text.isEmpty() || externalError.value.isNotEmpty(),
            leadingIcon = if (icon.isNotEmpty()) {
                {
                    Icon(
                        painter = painterResource(resourcePath = "drawables/${icon}.svg"),
                        contentDescription = "$hint Icon",
                        tint = if (isRequired && field.text.isEmpty()) MaterialTheme.colorScheme.error
                        else LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                        modifier = ModifierFieldImages
                    )
                }
            } else null,
            trailingIcon = if (isRequired && field.text.isEmpty()) {
                {
                    Icon(
                        painter = painterResource(resourcePath = "drawables/high.svg"),
                        contentDescription = "Error $hint",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = ModifierFieldImages
                    )
                }
            } else null,
            visualTransformation = visualTransformation,
            modifier = modifier
        )
        // endregion
        Row(Modifier.padding(start = 16.dp, end = 12.dp)) {
            Text(
                text = if (field.text.isEmpty()) {
                    "Required"
                } else {
                    ""
                },
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )

            if (externalError.value.isNotEmpty() && field.text.isNotEmpty()) Text(
                text = externalError.value,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(Modifier.weight(1f))
            if (counterEnable) Text(
                text = "${field.text.length}/$counterNumber",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .alpha(0.5f)
            )
        }
    }

}
