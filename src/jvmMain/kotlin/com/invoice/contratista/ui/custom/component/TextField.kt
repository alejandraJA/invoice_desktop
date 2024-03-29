@file:OptIn(ExperimentalMaterial3Api::class)

package com.invoice.contratista.ui.custom.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.invoice.contratista.utils.HIGH
import com.invoice.contratista.utils.REQUIRED

@ExperimentalMaterial3Api
@Composable
fun TextField(
    model: TextFieldModel
) = Column(modifier = model.modifier) {
    var field by rememberSaveable { mutableStateOf(TextFieldValue(model.init)) }
    val isFieldEmpty = field.text.isEmpty()
    val hasError = model.externalError.value.isNotEmpty()
    val isRequiredAndEmpty = model.isRequired && isFieldEmpty
    val isError = isRequiredAndEmpty || hasError

    OutlinedTextField(
        value = field,
        onValueChange = { newValue ->
            field = newValue
            model.change.invoke(newValue.text)
        },
        label = {
            val labelHint = "${model.hint}${if (model.isRequired) "*" else ""}"
            Text(text = labelHint)
        },
        placeholder = model.placeholder.takeIf { it.isNotEmpty() }?.let {
            { Text(text = it) }
        },
        isError = isError,
        leadingIcon = model.icon.takeIf { it.isNotEmpty() }?.let { icon ->
            {
                Icon(
                    painter = painterResource(resourcePath = "drawables/$icon.svg"),
                    contentDescription = "${model.hint} Icon",
                    tint =
                    if (isError) MaterialTheme.colorScheme.error
                    else LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                    modifier = ModifierFieldImages
                )
            }
        },
        trailingIcon = if (model.trailingIcon.isNotEmpty() && !(model.isRequired && isFieldEmpty)) {
            {
                Icon(
                    painter = painterResource(resourcePath = "drawables/high.svg"),
                    contentDescription = "Error $model.hint",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = ModifierFieldImages
                )
            }
        } else if (model.isRequired && isFieldEmpty) {
            {
                Icon(
                    painter = painterResource(resourcePath = "drawables/high.svg"),
                    contentDescription = "Error $model.hint",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = ModifierFieldImages
                )
            }
        } else null,
        visualTransformation = model.visualTransformation,
        modifier = model.modifier
    )

    TextFieldErrorText(textFieldModel = model, isRequiredAndEmpty = isRequiredAndEmpty)

    if (model.counterEnable) {
        TextFieldCounterText(field = field, counterNumber = model.counterNumber)
    }
}


@Composable
private fun TextFieldErrorText(
    textFieldModel: TextFieldModel,
    isRequiredAndEmpty: Boolean
) {
    Text(
        text = if (isRequiredAndEmpty) REQUIRED
        else textFieldModel.externalError.value.ifEmpty { "" },
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun TextFieldCounterText(
    field: TextFieldValue,
    counterNumber: Int
) {
    Text(
        text = "${field.text.length}/$counterNumber",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp).alpha(0.5f)
    )
}

class TextFieldModel(
    val init: String = "",
    val hint: String,
    val change: (String) -> Unit,
    val placeholder: String = "",
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val icon: String = HIGH,
    val isRequired: Boolean = false,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val counterEnable: Boolean = false,
    val externalError: MutableState<String> = mutableStateOf(""),
    val counterNumber: Int = 0,
    val trailingIcon: String = ""
)
