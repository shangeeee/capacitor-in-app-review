package io.shangeeee.capacitor.inappreview;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

@NativePlugin
public class InAppReview extends Plugin {

   private ReviewManager manager;

   InAppReview() {
       super();
       manager =  ReviewManagerFactory.create(  this.getContext());
   }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);
    }

    @PluginMethod
    public  void  launchReview(final PluginCall call) {

        Task<ReviewInfo> request = this.manager.requestReviewFlow();

        request.addOnCompleteListener((new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                        // We can get the ReviewInfo object
                        ReviewInfo reviewInfo = task.getResult();
                        Task<Void> flow = manager.launchReviewFlow(getActivity(), reviewInfo);
                        flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
                                // The flow has finished. The API does not indicate whether the user
                                // reviewed or not, or even whether the review dialog was shown. Thus, no
                                // matter the result, we continue our app flow.
                                JSObject ret = new JSObject();
                                ret.put("value", task);
                               call.success(ret);
                            }
                        });
                    } else {
                    call.error("launchReview : Encountered an error somewhere");
                    }
                }

        }));
    }


}
