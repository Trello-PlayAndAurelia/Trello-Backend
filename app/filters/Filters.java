package filters;

import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;

import javax.inject.Inject;

/**
 * Created by Adam Piech on 2016-11-16.
 */

public class Filters extends DefaultHttpFilters {

    @Inject
    public Filters(CORSFilter corsFilter) {
        super(corsFilter);
    }

}