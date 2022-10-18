package com.example.connect.utilites.photo

import android.content.Context
import android.content.res.TypedArray
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.connect.R
import com.example.connect.databinding.LayoutFieldPhotoKtpBinding
import com.example.connect.databinding.LayoutPhotoAvatarBinding
import com.example.connect.utilites.SpanUtil
import com.example.connect.utilites.app.UploadCallbackWithCrop
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

@AndroidEntryPoint
class PhotoFieldViewWithCropper(
    context: Context,
    attrs: AttributeSet
) :
    ConstraintLayout(context, attrs),
    UploadCallbackWithCrop {
    private var callback: UploadCallbackWithCrop
    private var photoBottomSheet: PhotoBottomSheetWithCropper
    var image: File? = null
    var imageBase64: String? = null
    var title: String = ""
    private var photoHolder: ImageView
    private val type: String
    private var styledAttributes: TypedArray
    private var optional = false
    private var photoFieldListener: UploadListener? = null
    var isPreviewEnabled = false
    private var binding: LayoutFieldPhotoKtpBinding
    private var bindingAvatar: LayoutPhotoAvatarBinding


    init {
        binding = LayoutFieldPhotoKtpBinding.inflate(LayoutInflater.from(context))
        bindingAvatar = LayoutPhotoAvatarBinding.inflate(LayoutInflater.from(context))
        attrs.let {
            styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.PhotoFieldView, 0, 0)
            type = styledAttributes.getString(R.styleable.PhotoFieldView_type_image) ?: "0"
            optional = styledAttributes.getBoolean(R.styleable.PhotoFieldView_optional, false)
        }
        if (type == "0") {
            inflate(context, R.layout.layout_photo_field_collateral, this)
            binding.tvUploadPhoto.setOnClickListener {
                clickUpload()
            }
            val text = styledAttributes.getString(R.styleable.PhotoFieldView_text_image)
            setText(text)
            photoHolder = binding.imgPhoto
        } else {
            inflate(context, R.layout.layout_photo_avatar, this)

            val enabled = styledAttributes.getBoolean(R.styleable.PhotoFieldView_enabled, true)
            if (!enabled) {
                bindingAvatar.ivTakePhotoAvatar.visibility = View.GONE
            } else {
                bindingAvatar.containerPhotoAvatar.setOnClickListener {
                    clickUpload()
                }
            }
            photoHolder = bindingAvatar.ivAvatar
        }
        callback = this
        context as FragmentActivity
        photoBottomSheet = PhotoBottomSheetWithCropper(callback, true)
    }

    private fun clickUpload() {
        if (photoFieldListener != null) {
            photoFieldListener!!.onUploadPicture()
        }

        try {
            photoBottomSheet.show(
                (context as FragmentActivity).supportFragmentManager,
                "photoBottomSheet"
            )
        } catch (e: Exception) {
//            displayToast(
//                context,
//                context.getString(R.string.label_an_error_occured) + " ${e.message}"
//            )
        }
    }

    fun isPickerEnabled(value: Boolean = true) {
        if (!value) {
            binding.tvUploadPhoto.setOnClickListener {
                // Do Nothing
            }
            binding.tvUploadPhoto.setTextColor(ContextCompat.getColor(context, R.color.grey_cf))
        } else {
            binding.tvUploadPhoto.setTextColor(ContextCompat.getColor(context, R.color.red50))
            binding.tvUploadPhoto.setOnClickListener {
                clickUpload()
            }
        }
    }

    fun setText(text: String?, required: Boolean = false) {
        title = text.toString()
        if (text != null) {
            if (!optional || required) {
                SpanUtil.createMarkRequiredSpan(binding.tvPhotoTitle, title)
            } else {
                binding.tvPhotoTitle.text = title
            }
        }
    }

    fun setRequired(value: Boolean = true) {
        if (value) {
            SpanUtil.createMarkRequiredSpan(binding.tvPhotoTitle, title)
        } else {
            binding.tvPhotoTitle.text = title
        }
    }

    fun setTextError(text: String = "") {
        binding.viewDivider.visibility = View.VISIBLE
        binding.tvHelper.visibility = View.VISIBLE
//        binding.tvHelper.setTextColor(ContextCompat.getColor(context, R.color.red))
        if (text != "") {
            binding.tvHelper.text = text
        }
    }

    private fun clearTextError() {
        binding.viewDivider.visibility = View.GONE
        binding.tvHelper.visibility = View.GONE
        binding.tvHelper.setTextColor(ContextCompat.getColor(context, R.color.black1))
    }

//    fun url(imageUrl: String = "", imageFile: File? = null) {
//        if (imageUrl.isNotEmpty() || imageFile.isNotNull()) {
//            Glide.with(this).asBitmap().load(imageFile ?: imageUrl)
//                .error(R.drawable.ic_placeholder_photo)
//                .dontTransform()
//                .placeholder(R.drawable.ic_placeholder_photo)
//                .into(object : CustomTarget<Bitmap>() {
//                    override fun onResourceReady(
//                        resource: Bitmap,
//                        transition: Transition<in Bitmap>?
//                    ) {
//                        imageBase64 = getBase64(resource)
//                        tvUploadPhoto.text = getStringResource(R.string.label_action_upload_again)
//                        photoHolder.setImageBitmap(resource)
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {
//                    }
//                })
//        }
//    }
//
//    fun containsImage(setError: Boolean): Boolean {
//        if (imageBase64.isNull()) {
//            if (setError)
//                setTextError()
//        }
//        return imageBase64.isNotNull()
//    }
//
    interface UploadListener {
        fun onUploadPicture()

        fun onFinish(file: File)
    }

    fun addUploadListner(listener: UploadListener) {
        if (photoFieldListener != null) {
            throw Exception("listener has assigned")
        }

        photoFieldListener = listener
    }
//
    override fun uploadWithCropper(uri: Uri) {
        val file = File(uri.path.toString())

        try {

            if (type == "0") {
                clearTextError()
            }

            image = file
            val bm = BitmapFactory.decodeFile(image!!.path)
            val adjustedBitmap = adjustImageRotation(bm, file)
            imageBase64 = getBase64(adjustedBitmap)
            binding.tvUploadPhoto.text = "Unggah Lagi"
            photoHolder.setImageBitmap(adjustedBitmap)

            if (photoFieldListener != null) {
                photoFieldListener!!.onFinish(file)
            }
            photoHolder.setImageURI(uri)
            photoBottomSheet.dismiss()
        } catch (e: IOException) {
            if (type == "0") {
                setTextError()
            }
        }
    }
}