package appinventor.ai_Odukabdulbasit.Zikirmatik

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class rowAdapter(val context: Context, val listItem : ArrayList<String>) : BaseAdapter() {

    private class ViewHolder(row : View?){
        var textTV : TextView

        init {
            this.textTV = row?.findViewById(R.id.rowTextView) as TextView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view : View?
        var  viewHolder : ViewHolder

        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.row, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var eleman : String = getItem(position) as String
        viewHolder.textTV.text = eleman

        return view as View
    }

    override fun getItem(position: Int): Any {
        return listItem[position]//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()//To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return listItem.count()//To change body of created functions use File | Settings | File Templates.
    }
}