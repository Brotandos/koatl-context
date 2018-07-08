package com.brotandos.koatlcontextlib

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.jetbrains.anko.*

interface KoatlContext<out T> : AnkoContext<T> {
    val Int.dp: Int get() = ctx.dip(this)

    val visible: View.() -> Unit get() = {
        visibility = View.VISIBLE
    }

    val invisible: View.() -> Unit get() = {
        visibility = View.INVISIBLE
    }

    val hidden: View.() -> Unit get() = {
        visibility = View.GONE
    }

    val enabled: View.() -> Unit get() = {
        isEnabled = true
    }

    val disabled: View.() -> Unit get() = {
        isEnabled = false
    }

    val clickable: View.() -> Unit get() = {
        isClickable = true
    }

    val unclickable: View.() -> Unit get() = {
        isClickable = false
    }

    val checked: CompoundButton.() -> Unit get() = {
        isChecked = true
    }

    val unchecked: CompoundButton.() -> Unit get() = {
        isChecked = false
    }

    val <T: View> T.asSubmissive: T  get() {
        layoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)
        return this
    }

    val <T: View> T.asColumn: T get() {
        layoutParams = ViewGroup.LayoutParams(wrapContent, matchParent)
        return this
    }

    val <T: View> T.asRow: T get() {
        layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
        return this
    }

    val <T: View> T.asDominant: T get() {
        layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        return this
    }

    val lpRow: ViewGroup.LayoutParams.() -> Unit get() = {
        width = matchParent
        height = wrapContent
    }

    /**
     * 'bg' stands for 'background'
     * */
    fun bg(res: Int): View.() -> Unit = {
        backgroundResource = res
    }

    fun bgColor(color: Int): View.() -> Unit = {
        backgroundColor = color
    }

    /**
     * Listeners setting functions
     * */
    operator fun View.OnClickListener.unaryPlus(): View.() -> Unit = {
        setOnClickListener(this@unaryPlus)
    }

    operator fun CompoundButton.OnCheckedChangeListener.unaryMinus(): CompoundButton.() -> Unit = {
        setOnCheckedChangeListener(this@unaryMinus)
    }

    operator fun View.OnFocusChangeListener.unaryMinus(): View.() -> Unit = {
        onFocusChangeListener = this@unaryMinus
    }

    operator fun View.OnTouchListener.not(): View.() -> Unit = {
        setOnTouchListener(this@not)
    }

    operator fun BottomNavigationView.OnNavigationItemSelectedListener.invoke(): BottomNavigationView.() -> Unit = {
        setOnNavigationItemSelectedListener(this@invoke)
    }

    operator fun Int.not(): View.() -> Unit = {
        id = this@not
    }

    fun id(id: Int): View.() -> Unit = {
        this.id = id
    }

    fun tag(t: Any): View.() -> Unit = {
        tag = t
    }

    operator fun Drawable.unaryPlus(): View.() -> Unit = {
        background = this@unaryPlus
    }

    /**
     * Padding settings functions
     * 'p' stands for 'padding'
     * 'c' stands for 'coefficient'
     * */
    fun p(c: Int): View.() -> Unit = {
        setPadding(c, c, c, c)
    }

    fun p(h: Int, v: Int): View.() -> Unit = {
        setPadding(h, v, h, v)
    }

    fun p(l: Int, t: Int, r: Int, b: Int): View.() -> Unit = {
        setPadding(l, t, r, b)
    }

    fun pLeft(c: Int): View.() -> Unit = {
        setPadding(c, paddingTop, paddingRight, paddingBottom)
    }

    fun pTop(c: Int): View.() -> Unit = {
        setPadding(paddingLeft, c, paddingRight, paddingBottom)
    }

    fun pRight(c: Int): View.() -> Unit = {
        setPadding(paddingLeft, paddingTop, c, paddingBottom)
    }

    fun pBottom(c: Int): View.() -> Unit = {
        setPadding(paddingLeft, paddingTop, paddingRight, c)
    }

    fun pHorizontal(c: Int): View.() -> Unit = {
        setPadding(c, paddingTop, c, paddingBottom)
    }

    fun pVertical(c: Int): View.() -> Unit = {
        setPadding(paddingLeft, c, paddingRight, c)
    }

    fun selected(selectedItemId: Int): BottomNavigationView.() -> Unit = {
        this.selectedItemId = selectedItemId
    }

    val stretch: TableLayout.() -> Unit
        get() = { isStretchAllColumns = true }

    val Float.asWeightSum: TableRow.() -> Unit get() = {
        weightSum = this@asWeightSum
    }

    // TextView extensions
    val underlined: TextView.() -> Unit get() = {
        paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    val textBlack: TextView.() -> Unit get() = {
        textColor = Color.BLACK
    }

    val text4: TextView.() -> Unit get() = {
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    }

    val text5: TextView.() -> Unit get() = {
        textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    val text6: TextView.() -> Unit get() = {
        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
    }

    val gText1: TextView.() -> Unit
        get() = { gravity = Gravity.TOP or Gravity.START }
    val gText2: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP }
    val gText3: TextView.() -> Unit
        get() = { gravity = Gravity.END or Gravity.TOP }
    val gText4: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_VERTICAL or Gravity.START}
    val gText5: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL }
    val gText6: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_VERTICAL or Gravity.END }
    val gText7: TextView.() -> Unit
        get() = { gravity = Gravity.START or Gravity.BOTTOM }
    val gText8: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM }
    val gText9: TextView.() -> Unit
        get() = { gravity = Gravity.END or Gravity.BOTTOM }
    val gText123: TextView.() -> Unit
        get() = { gravity = Gravity.TOP }
    val gText147: TextView.() -> Unit
        get() = { gravity = Gravity.START }
    val gText258: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_HORIZONTAL }
    val gText456: TextView.() -> Unit
        get() = { gravity = Gravity.CENTER_VERTICAL }
    val gText369: TextView.() -> Unit
        get() = { gravity = Gravity.END }
    val gText789: TextView.() -> Unit
        get() = { gravity = Gravity.BOTTOM }

    val password: TextView.() -> Unit get() = {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    val numeric: TextView.() -> Unit get() = {
        inputType = InputType.TYPE_CLASS_NUMBER
    }

    val floatNumeric: TextView.() -> Unit get() = {
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    }

    val phone: TextView.() -> Unit get() = {
        inputType = InputType.TYPE_CLASS_PHONE
    }

    val bold: TextView.() -> Unit get() = {
        typeface = Typeface.DEFAULT_BOLD
    }

    val Int.lined: TextView.() -> Unit get() = {
        when {
            this@lined == 1 -> singleLine = true
            this@lined > 1 -> lines = this@lined
            else -> RuntimeException("Must be natural number")
        }
    }

    val Int.maxLines: TextView.() -> Unit get() = {
        when {
            this@maxLines == 1 -> singleLine = true
            this@maxLines > 1 -> maxLines = this@maxLines
            else -> RuntimeException("Must be natural number")
        }
    }

    val Int.minLines: TextView.() -> Unit get() = {
        if (this@minLines < 1) RuntimeException("Must be natural number")
        else minLines = this@minLines
    }

    val Float.sp: TextView.() -> Unit get() = {
        textSize = sp(this@sp).toFloat()
    }

    val selectable: TextView.() -> Unit get() = {
        setTextIsSelectable(true)
    }

    val String.html: CharSequence get()
    =   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) Html.fromHtml(this)
        else Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)

    fun text(color: Int): TextView.() -> Unit = {
        textColor = color
    }

    operator fun String.unaryPlus(): TextView.() -> Unit = {
        hint = this@unaryPlus
    }

    fun <T: TextView> T.html(text: String) {
        this.text =
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) Html.fromHtml(text)
                else Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    }

    /**
     * Icon setting functions
     * */
    fun icLeft(iconRes: Int): TextView.() -> Unit = {
        setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0)
    }

    fun icRight(iconRes: Int): TextView.() -> Unit = {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, iconRes, 0)
    }

    fun icLeftRight(leftIcon: Int, rightIcon: Int): TextView.() -> Unit = {
        setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, rightIcon, 0)
    }

    fun icTopRight(topIcon: Int, rightIcon: Int): TextView.() -> Unit = {
        setCompoundDrawablesWithIntrinsicBounds(0, topIcon, rightIcon, 0)
    }

    fun pIcon(c: Int): TextView.() -> Unit = {
        compoundDrawablePadding = c
    }


    fun <T> T.createKoatlContext (
            ctx: Context,
            init: KoatlContext<T>.() -> Unit,
            setContentView: Boolean = false
    ) : KoatlContext<T> {
        val dsl = KoatlContextImpl(ctx, this, setContentView)
        dsl.init()
        return dsl
    }

}

open class KoatlContextImpl<T> (
        override val ctx: Context,
        override val owner: T,
        setContentView: Boolean
) : AnkoContextImpl<T>(ctx, owner, setContentView), KoatlContext<T>

inline fun Context.KUI(init: KoatlContext<Context>.() -> Unit): android.view.View =
        createKoatlContext(this, init)


inline fun <T> createKoatlContext (
        ctx: Context,
        init: KoatlContext<T>.() -> Unit,
        setContentView: Boolean = false
): android.view.View {
    val dsl = KoatlContextImpl(ctx, ctx, setContentView)
    (dsl as KoatlContext<T>).init()
    return dsl.view
}


inline fun <T> koatlContext (
        ctx: Context,
        owner: T,
        setContentView: Boolean,
        init: KoatlContext<T>.() -> Unit
): View {
    val dsl = KoatlContextImpl(ctx, owner, setContentView)
    (dsl as KoatlContext<T>).init()
    return dsl.view
}


fun <E> RecyclerView.forEachOf (
        items: List<E>,
        handleLayoutParams: ViewGroup.LayoutParams.() -> Unit = row,
        holderView: KoatlContext<ViewGroup>.(E, Int) -> Unit
): RecyclerView {
    adapter = object : RecyclerView.Adapter<KoatlViewHolder<E>>() {
        override fun getItemCount() = items.size

        override fun onCreateViewHolder(parent: ViewGroup, itemViewType: Int)
                = KoatlViewHolder(FrameLayout(parent.context), parent, holderView, handleLayoutParams)

        override fun onBindViewHolder(holder: KoatlViewHolder<E>, position: Int)
                = holder.bind(items[holder.adapterPosition], holder.adapterPosition)
    }
    return this
}


class KoatlViewHolder<in E> (
        val vItem: FrameLayout,
        private val parent: ViewGroup,
        private val holderViewMarkup: KoatlContext<ViewGroup>.(E, Int) -> Unit,
        handleItemViewLayoutParams: ViewGroup.LayoutParams.() -> Unit
): RecyclerView.ViewHolder(vItem) {
    init {
        vItem.layoutParams = ViewGroup.LayoutParams(wrapContent, wrapContent)
        vItem.layoutParams.handleItemViewLayoutParams()
    }

    fun bind(item: E, position: Int) {
        val koatl = KoatlContextImpl(parent.context, parent, false)
                .apply { holderViewMarkup(item, position) }
        vItem.addView(koatl.view)
    }
}


inline val submissive: ViewGroup.LayoutParams.() -> Unit
    get() = { width = wrapContent; height = wrapContent }
inline val row: ViewGroup.LayoutParams.() -> Unit
    get() = { width = matchParent; height = wrapContent }
inline val column: ViewGroup.LayoutParams.() -> Unit
    get() = { width = wrapContent; height = matchParent }
inline val dominant: ViewGroup.LayoutParams.() -> Unit
    get() = { width = matchParent; height = matchParent }