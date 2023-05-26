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
import com.invoice.contratista.utils.HIGH
import com.invoice.contratista.utils.REQUIRED

@ExperimentalMaterial3Api
@Composable
fun TextField(
    textFieldModel: TextFieldModel
) {
    var field by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = textFieldModel.initField))
    }

    val isFieldEmpty = field.text.isEmpty()
    val hasError = textFieldModel.externalError.value.isNotEmpty()
    val isRequiredAndEmpty = textFieldModel.isRequired && isFieldEmpty
    val isError = isRequiredAndEmpty || hasError

    Column(modifier = textFieldModel.modifier) {
        if (textFieldModel.initField.isNotEmpty()) {
            textFieldModel.change.onChange(textFieldModel.initField)
        }

        OutlinedTextField(
            value = field,
            onValueChange = { newValue ->
                field = newValue
                textFieldModel.change.onChange(newValue.text)
            },
            label = {
                val labelHint = "${textFieldModel.hint}${if (textFieldModel.isRequired) "*" else ""}"
                Text(text = labelHint)
            },
            placeholder = textFieldModel.placeholder.takeIf { it.isNotEmpty() }?.let {
                { Text(text = it) }
            },
            isError = isError,
            leadingIcon = textFieldModel.icon.takeIf { it.isNotEmpty() }?.let { icon ->
                {
                    Icon(
                        painter = painterResource(resourcePath = "drawables/$icon.svg"),
                        contentDescription = "${textFieldModel.hint} Icon",
                        tint =
                        if (isError) MaterialTheme.colorScheme.error
                        else LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                        modifier = ModifierFieldImages
                    )
                }
            },
            trailingIcon = textFieldModel.isRequired.takeIf { isFieldEmpty }?.let {
                {
                    Icon(
                        painter = painterResource(resourcePath = "drawables/high.svg"),
                        contentDescription = "Error ${textFieldModel.hint}",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = ModifierFieldImages
                    )
                }
            },
            visualTransformation = textFieldModel.visualTransformation,
            modifier = textFieldModel.modifier
        )

        TextFieldErrorText(textFieldModel = textFieldModel, isRequiredAndEmpty = isRequiredAndEmpty)

        if (textFieldModel.counterEnable) {
            TextFieldCounterText(field = field, counterNumber = textFieldModel.counterNumber)
        }
    }
}

@Composable
fun TextFieldErrorText(
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
fun TextFieldCounterText(
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
    val hint: String,
    val change: OnValueChange,
    val placeholder: String = "",
    val initField: String = "",
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val icon: String = HIGH,
    val isRequired: Boolean = false,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val counterEnable: Boolean = false,
    val externalError: MutableState<String> = mutableStateOf(""),
    val counterNumber: Int = 0
)
