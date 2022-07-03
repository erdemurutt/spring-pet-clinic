package com.erdemurut.springframework.sfgpetclinic.controllers;

import com.erdemurut.springframework.sfgpetclinic.model.Owner;
import com.erdemurut.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RequestMapping("/owners")
@Controller
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}

	@GetMapping
	public String processFindForm(Model model, Owner owner, BindingResult bindingResult) {
		if (owner.getLastName() == null) {
			owner.setLastName("");
		}

		List<Owner> results = ownerService.findByLastNameContainingIgnoreCase(owner.getLastName().toLowerCase(Locale.ROOT));
		if (results.isEmpty()) {
			bindingResult.rejectValue("lastName", "notFound", "Not Found");
			return "redirect:/owners";
		} else if (results.size() == 1) {
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}

	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(ownerService.findById(ownerId));
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult bindingResult,
										 @PathVariable Long ownerId) {
		if (bindingResult.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			owner.setId(ownerId);
			Owner savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

}
