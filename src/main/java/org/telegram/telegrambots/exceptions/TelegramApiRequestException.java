/*
 * This file is part of TelegramBots.
 *
 * TelegramBots is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TelegramBots is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TelegramBots.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.telegram.telegrambots.exceptions;

import org.json.JSONObject;
import org.telegram.telegrambots.api.objects.ResponseParameters;


/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Exception thrown when something goes wrong in the api
 * @date 14 of January of 2016
 */
public class TelegramApiRequestException extends TelegramApiException {
    private static final String ERRORDESCRIPTIONFIELD = "description";
    private static final String ERRORCODEFIELD = "error_code";
    private static final String PARAMETERSFIELD = "parameters";

    private String apiResponse = null;
    private Integer errorCode = 0;
    private ResponseParameters parameters;

    public TelegramApiRequestException(String message) {
        super(message);
    }

    public TelegramApiRequestException(String message, JSONObject object) {
        super(message);
        apiResponse = object.getString(ERRORDESCRIPTIONFIELD);
        errorCode = object.getInt(ERRORCODEFIELD);
        if (object.has(PARAMETERSFIELD)) {
            parameters = new ResponseParameters(object.getJSONObject(PARAMETERSFIELD));
        }
    }

    public TelegramApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public ResponseParameters getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        if (apiResponse == null) {
            return super.toString();
        } else if (errorCode == null) {
            return super.toString() + ": " + apiResponse;
        } else {
            return super.toString() + ": [" + errorCode + "] " + apiResponse;
        }
    }
}
