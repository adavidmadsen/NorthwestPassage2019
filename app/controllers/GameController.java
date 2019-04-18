package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

public class GameController extends Controller
{
    final private String CHARACTER_NAME_SESSION_KEY = "CHARACTER_NAME";
    final private String CREW_MEMBERS_SESSION_KEY = "CREW_MEMBERS";

    private FormFactory formFactory;


    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart(Http.Request request)
    {
        String crewMembers = "10";
        DynamicForm form = formFactory.form().bindFromRequest(request);
        String characterName = form.get("charactername");
        return ok(views.html.start.render()).addingToSession(request, CHARACTER_NAME_SESSION_KEY, characterName)
                .addingToSession(request, CREW_MEMBERS_SESSION_KEY, crewMembers);
    }


    public Result postEastFromEngland(Http.Request request)
    {
        String crewSize = request.session().getOptional(CREW_MEMBERS_SESSION_KEY).orElse("0");
        String newCrewSize = removeCrewMember(crewSize);


        if (Integer.parseInt(newCrewSize) >= 5)
        {
            return ok(views.html.eastfromengland.render(newCrewSize)).addingToSession(request,
                    CREW_MEMBERS_SESSION_KEY, newCrewSize);
        }
        else
        {
            return ok(views.html.oakisland.render());
        }
    }

    public Result postNorthEnd(Http.Request request)
    {
        String characterName = request.session().getOptional(CHARACTER_NAME_SESSION_KEY).orElse("Unknown");
        return ok(views.html.northend.render(characterName));
    }

    public Result postWestFromEngland(Http.Request request)
    {
        String crewSize = request.session().getOptional(CREW_MEMBERS_SESSION_KEY).orElse("0");
        String newCrewSize = removeCrewMember(crewSize);


        if (Integer.parseInt(newCrewSize) >= 5)
        {
            return ok(views.html.westfromengland.render(newCrewSize)).addingToSession(request,
                    CREW_MEMBERS_SESSION_KEY, newCrewSize);
        }
        else
        {
            return ok(views.html.oakisland.render());
        }
    }

    public Result postEastEnd(Http.Request request)
    {
        String characterName = request.session().getOptional(CHARACTER_NAME_SESSION_KEY).orElse("0");
        return ok(views.html.eastend.render(characterName));
    }

    public Result postWestEnd(Http.Request request)
    {
        String characterName = request.session().getOptional(CHARACTER_NAME_SESSION_KEY).orElse("Unknown");
        return ok(views.html.westend.render(characterName));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render());
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }

    public Result postFirstNorthStop()
    {
        return ok(views.html.firstNorthStop.render());
    }

    public Result getStart()
    {
        return ok(views.html.start.render());
    }

    private String removeCrewMember(String currentCrew)
    {
        int crewMembers = Integer.parseInt(currentCrew);
        int removeCrewMember = crewMembers - 1;

        return Integer.toString(removeCrewMember);

    }


}
