package com.teamshunya.silencio.Rest;


import com.teamshunya.silencio.Models.ArrivalList;
import com.teamshunya.silencio.Models.Departure;
import com.teamshunya.silencio.Models.DepartureList;
import com.teamshunya.silencio.Models.OfferList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by himanshusingh on 19/03/17.
 */

public interface APIInterface {
    @GET("/arrival")
    Call<ArrivalList> getFlightDetails();

    @GET("/departure")
    Call<DepartureList> getFlightDetail();

    @GET("/offers")
    Call<OfferList> getOfferDetail();

    @GET("/departure/{pnr}")
    Call<Departure> getmyDetail(@Path("pnr")String pnr);
}
