package com.s59160969.least

import android.app.Application
import com.s59160969.least.database.LeastDatabaseDAO
import androidx.lifecycle.AndroidViewModel

class HistoryViewModel (val database: LeastDatabaseDAO, application: Application) : AndroidViewModel(application) {

}
