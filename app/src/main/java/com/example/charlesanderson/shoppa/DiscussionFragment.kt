package com.example.charlesanderson.shoppa


import android.app.Fragment
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.ExecutionException

//result
//-- data
//---- children
//------ data
//-------- author
//-------- thumbnail
//-------- created_utc
//-------- score
//-------- title
//Try something like this
//
//for (int i = 0; i < hotTopics.length(); i++) {
//    JSONObject topic = hotTopics.getJSONObject(i).getJSONObject("data");
//
//    String author = topic.getString("author");
//    String imageUrl = topic.getString("thumbnail");
//    String postTime = topic.getString("created_utc");
//    String rScore = topic.getString("score");
//    String title = topic.getString("title");
//
//    topicdata.add(new ListData(title, author, imageUrl, postTime, rScore));
//    Log.v(DEBUG_TAG, topicdata.toString());
//}

/**
 * A simple [Fragment] subclass.
 */
class DiscussionFragment : android.support.v4.app.Fragment() {

    internal lateinit var commentList: ArrayList<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_discussion, container, false)
        commentList = ArrayList<String>()

        try {
            System.out.println("Discussion Fragment")
            System.out.println((activity as TabActivity).jsonData)
            val post = (activity as TabActivity).jsonData;
            val commentsUrl = Constants.REDDIT_URL + post.getString("permalink") + Constants.JSON_API_SUFFIX
            val commentsData = AsyncJSON().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, commentsUrl).get()

            // Set title bar
            (activity as TabActivity).setCustomActionBar(post.getString("title"))

            val commentsJson = JSONArray(commentsData)
            parseComments(commentsJson)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, commentList)
        //val listView = view.findViewById<View>(R.id.expandableListView) as ExpandableListView
        val listView = view.findViewById<View>(R.id.listView) as ListView
        listView.adapter = adapter
        return view
    }

    fun parseComments(json: JSONArray) {
        val comments = json.getJSONObject(1).getJSONObject("data").getJSONArray("children")
        for (i in 0..(comments.length() - 1)) {
            val comment = (comments[i] as JSONObject).get("data") as JSONObject
            System.out.println(comment)

            if(comment.has("body")) {
                this.commentList.add(comment.getString("body"))
                System.out.println(comment.getString("body"))

            }
        }
    }
}
