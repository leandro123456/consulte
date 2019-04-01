package com.lgg.nticxs.web.helper;


/**
 * Created by movasim on 19/09/16.
 */
public class CampaignHelper {

    //private static final int MAX_WAIT_TIME = 600; // 5 minutes
    @SuppressWarnings("unused")
	private static final int MAX_WAIT_TIME = 10;

    @SuppressWarnings("unused")
	public static boolean isAnyOperationRunning(String eid) {

//        CampaignDAO campaignDAO = new CampaignDAO();
//        Campaign campaign = campaignDAO.retrieveLastByEid(eid);

//        if (campaign == null)
//           return false;
//
//       if (campaign.getStatus().compareTo("FINISHED") == 0)
//           return false;

        // TODO: improve this
//        if (campaign.getStatus().compareTo("CREATED") == 0) {
//
 //           Date now = new Date();
//            int secondsBetween = (int) ((now.getTime() - campaign.getUpdatedAt().getTime()) / 1000);

//            if (secondsBetween > MAX_WAIT_TIME) {
//                return false;
//            }

//        }

        return false;
    }
}
