package com.example.flixster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class ShowFragment: Fragment(), OnShowListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = HorizontalLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        // Using the client, perform the HTTP request
        client [
                "https://api.themoviedb.org/3/trending/tv/week?api_key=$API_KEY",
                params,
                object : JsonHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        //TODO - Parse JSON into Models
                        val resultsJSON : JSONArray = json.jsonObject.getJSONArray("results") as JSONArray
                        val showsRawJSON: String = resultsJSON.toString()

                        val gson = Gson()
                        val arrayShowType = object : TypeToken<List<Show>>() {}.type
                        val models : List<Show> = gson.fromJson(showsRawJSON, arrayShowType)
                        recyclerView.adapter = ShowAdapter(models, this@ShowFragment)

                        // Look for this in Logcat:
                        Log.d("ShowFragment", showsRawJSON)
                    }
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("ShowFragment", errorResponse)
                        }
                    }
                }]

    }

    override fun onItemClick(item: Show) {
        val intent = Intent(this.context, InfoActivity::class.java)

        intent.putExtra("title", item.title)
        intent.putExtra("imageURL", "https://image.tmdb.org/t/p/w500/${item.showImageUrl}")
        intent.putExtra("description", item.description)

        startActivity(intent)
    }
}
